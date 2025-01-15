/* Clases */
class Equipo {
  constructor(
    id,
    cliente,
    propiedad,
    serie,
    tipo_equipo,
    marca,
    modelo,
    servicio,
    ubicacion,
    clienteide
  ) {
    this.id = id;
    this.cliente = cliente;
    this.propiedad = propiedad;
    this.serie = serie;
    this.tipo_equipo = tipo_equipo;
    this.marca = marca;
    this.modelo = modelo;
    this.servicio = servicio;
    this.ubicacion = ubicacion;
    this.clienteide = clienteide;
  }
}
/* fin clases */

eventoDescargarCsv();

/* cargados */
/* En esta seccion tengo que mostrar los equipos cargados */
mostrarListado();
eventoBotonVaciarEquipos();

async function mostrarListado() {
  let losEquipos = [];
  const info = await fetch("equipos")
    .then((response) => response.json())
    .then((data) => {
      //console.log("--- MUESTRO DATA CRUDA --- ", data);
      data.forEach(async (i) => {
        
        let e = new Equipo(
          String(i.id),
          String(i.cliente),
          String(i.propiedad),
          String(i.serie),
          String(i.tipo_equipo),
          String(i.marca),
          String(i.modelo),
          String(i.servicio),
          String(i.ubicacion),
          String(i.clienteide)
        );
        /* let e = new Equipo(
          String(i[0]),
          String(i[1]),
          String(i[2]),
          String(i[3]),
          String(i[4]),
          String(i[5]),
          String(i[6]),
          String(i[7]),
          String(i[8]),
          String(i[9])
        ); */
       // console.log("muestro info de equipo que llega: ", e);
        losEquipos.push(e);
      });
      //console.log("muestro el array: ", losEquipos);
    });
   cargarPagina(losEquipos);
}

async function cargarPagina(equipos) {
  let tabla = document.getElementById("tabla-equipos-nb");

  /* let contador = 1; */
  equipos.forEach(async (dato) => {
    let trow = document.createElement("tr");

    /* Llenamos los datos del ROW */
    for (let i = 0; i < 5; i++) {
      let td = document.createElement("td");
      if (i == 0) {
        td.innerHTML = String(dato["id"]);
      }
      if (i == 1) {
        td.innerHTML = String(dato["serie"]);
      }
      if (i == 2) {
        td.innerHTML = String(dato["tipo_equipo"]);
      }
      if (i == 3) {
        td.innerHTML = String(dato["servicio"]);
      }
      if (i == 4) {
        td.innerHTML = `<a class="btn btn-danger btn-eliminar" id="${dato["id"]}">
                    <img src="imgs/trash-2-blanco.png" alt="">
                                </a>`;
      }
      trow.appendChild(td);
    }

    tabla.append(trow);
    /*  contador++; */
  });
  eventoBotones();
}

function eventoBotones() {
  let botones = document.getElementsByClassName("btn-eliminar");
  for (const boton of botones) {
    let { id } = boton;
    /* codigo para borrar 1 linea sin recargar pag
            agrego evento:
            boton.addEventListener("click", (event) => {
                 eliminarEquipo(id); 
                 event.target.parentNode-> acceso a td, si sumo .parendtnode+
                 accedo a tr, luego agrego .remove() y elimino la fila,
                 probar,pero deberia eliminar fila y no recargar pag entera.
                console.log(event.target.parentNode);
            }) */

    boton.onclick = () => {
      eliminarEquipo(id);
    };
  }
}

async function eliminarEquipo(id) {
  //metodo para enviar info a eliminar
  const resp = await fetch("equipos" + "/" + id, {
    method: "DELETE",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  });
  if (resp.ok) {
    location.reload();
  } else {
  }
  /*Recarga pagina:
      setTimeout(() => {
          location.reload();
      }, 2000);*/
}
function eventoBotonVaciarEquipos() {
  /* Boton vaciar listado equipos */
  let btnEquipos = document.getElementById("borrarEquipos");
  btnEquipos.addEventListener("click", vaciarListadoEquipos);
}

function vaciarListadoEquipos(e) {
  e.preventDefault();

  var resultado = window.confirm(
    "¿Estás seguro de borrar el listado completo?"
  );

  if (resultado === true) {
    const data = fetch("vaciar/equipos", {
      method: "DELETE",
    });
    location.reload();
  }
}

/* fin cargados */

function eventoDescargarCsv() {
  let btnDescarga = document.getElementById("descargarCsv");
  btnDescarga.addEventListener("click", solicitudDescarga);
  console.log("cargó evento descarga");
}

async function solicitudDescarga(e) {
  e.preventDefault();

  var contentDispo = "";

  fetch("descargarListado", { method: "GET" })
    .then((response) => {
      contentDispo = response.headers.get("Content-Disposition");

      return response.blob();
    })
    .then((blob) => {
      // Crea un objeto URL para el blob
      const url = window.URL.createObjectURL(blob);

      // Crea un elemento de enlace temporal y lo agrega al DOM
      const a = document.createElement("a");
      a.style.display = "none";
      a.href = url;
      a.download = contentDispo.split("filename=")[1];
      document.body.appendChild(a);

      // Simula un clic en el elemento de enlace para iniciar la descarga
      a.click();

      // Elimina el elemento de enlace y libera el objeto URL
      document.body.removeChild(a);
      window.URL.revokeObjectURL(url);
    });
}

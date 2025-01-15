eventoFormularioListas();
eventoBotonBorrar();
//eventoDescargarCsv();

function eventoFormularioListas() {
 /*  let form_clientes = document.getElementById("form-subida-clientes");
  let form_marcas = document.getElementById("form-subida-marcas");
  let form_servicios = document.getElementById("form-subida-servicios");
  let form_tipos = document.getElementById("form-subida-tipos");
  let form_modelos = document.getElementById("form-subida-modelos");

  form_clientes.addEventListener("submit", enviarListadoClientes);
  form_marcas.addEventListener("submit", enviarListadoMarcas);
  form_servicios.addEventListener("submit", enviarListadoServicios);
  form_tipos.addEventListener("submit", enviarListadoTipos);
  form_modelos.addEventListener("submit", enviarListadoModelos);
   */

  let nuevo_form = document.getElementById("nuevoFormularioListas");

  nuevo_form.addEventListener("submit", enviarCualquierListado);

 // console.log("carga eventoFormularioListas Listas.");
}

function enviarCualquierListado(e){
  e.preventDefault();

  let archivo = document.getElementById("input-listados").files[0];

  let elListado = document.getElementById("select-listados").value;
  
  if(!elListado) return alert("No se selección un listado correcto. -> " + elListado);
  if(!archivo) return alert("No se seleccionó un archivo para subir. PASO 1. ");
 
leerData2(archivo, String(elListado));
}

function enviarListadoClientes(e) {
  e.preventDefault();
  //Acá pregunto si se eligió archivo:
  let archivo = document.getElementById("input-clientes").files[0];

  leerData2(archivo, "clientes");
}

function enviarListadoMarcas(e) {
  e.preventDefault();
  //Acá pregunto si se eligió archivo:
  let archivo = document.getElementById("input-marcas").files[0];
  leerData2(archivo, "marcas");
}

function enviarListadoServicios(e) {
  e.preventDefault();
  //Acá pregunto si se eligió archivo:
  let archivo = document.getElementById("input-servicios").files[0];

  leerData2(archivo, "servicios");
}

function enviarListadoTipos(e) {
  e.preventDefault();
  //Acá pregunto si se eligió archivo:
  let archivo = document.getElementById("input-tipos").files[0];

  leerData2(archivo, "tipoEquipos");
}

function enviarListadoModelos(e) {
  e.preventDefault();
  //Acá pregunto si se eligió archivo:
  let archivo = document.getElementById("input-modelos").files[0];

  leerData2(archivo, "modelos");
}

function leerData(archivo, queTabla) {
  const arrayColumnasNombre = [];
  if (archivo) {
    console.log("Entro a leer archivo en metodo leerData");
    let reader = new FileReader();
    reader.addEventListener("load", (evt) => {
      //Acá obtengo el archivo subido:
      let datos = evt.target.result;

      /* Aca tengo que trabajar con el archivo CSV.
            antes de enviar la info al server.*/
      //Este parametro de split es para abarcar todo tipo de salto de linea
      let lasLineas = datos.split(/[\r\n]+/gm);
      //Aca podría empezar el indice i = 1 para saltarme el encabezado
      //si lo tuviera el archivo csv
      for (let i = 0; i < lasLineas.length - 1; i++) {
        let columna = lasLineas[i].split(";");
        //elijo columna 0 porque se guarda el nombre, dato que me interesa
        //si quiero otro cambio el indice de columna[].
        let dato = String(columna[1]).replaceAll(`"`, ""); //saco las comillas de los datos
        //IF: para filtrar los campos vacíos
        if (dato !== "") {
          arrayColumnasNombre.push(dato);
        }
      }
      /*  Todos los datos pero en un array*/
      enviarDataServer(arrayColumnasNombre, queTabla);
    });

    reader.readAsText(archivo);
  } else {
    alert("No se ha seleccionado un archivo.");
    return;
  }
}

function leerData2(archivo, queTabla) {
  /* Este metodo devuelve un objeto, no un string como el leerData 
    se creo debido a la necesidad de tener guardado nombre de cliente e ID*/

  const arrayColumnasObjeto = [];

  if (archivo) {
    console.log("Entro a leer archivo en metodo leerData");
    let reader = new FileReader();
    reader.addEventListener("load", (evt) => {
      //Acá obtengo el archivo subido:
      let datos = evt.target.result;

      /* Aca tengo que trabajar con el archivo CSV.
            antes de enviar la info al server.*/
      //Este parametro de split es para abarcar todo tipo de salto de linea
      let lasLineas = datos.split(/[\r\n]+/gm);
      //Aca podría empezar el indice i = 1 para saltarme el encabezado
      //si lo tuviera el archivo csv
      for (let i = 0; i < lasLineas.length - 1; i++) {
        let columna = lasLineas[i].split(";");
        //elijo columna 1 porque se guarda el nombre, dato que me interesa
        //si quiero otro cambio el indice de columna[].
        let idObjeto = String(columna[0]).replaceAll(`"`, ""); //saco las comillas de los datos
        let nombre = String(columna[1]).replaceAll(`"`, ""); //saco las comillas de los datos
        //IF: para filtrar los campos vacíos
        if (nombre !== "") {
          //creo objeto para armar el array de objetos
          let objeto = nombre + " separador " + idObjeto;
          //let objeto = { nombre, idCliente };

          //guardo el dato en array
          arrayColumnasObjeto.push(objeto);
        }
      }
      /*  Todos los datos pero en un array*/
      enviarDataServer(arrayColumnasObjeto, queTabla);
    });

    reader.readAsText(archivo);
  } else {
    alert("No se ha seleccionado un archivo.");
    return;
  }
}

async function enviarDataServer(dato, tabla) {
  url = "subirTablas/" + tabla;
  const variable = await fetch(url, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    // body: JSON.stringify(dato)  --> para enviar 1 por 1
    body: JSON.stringify({ datos: dato }),
  });
  if (variable.ok) {
    console.log("la informacion se ha enviado correctamente.");
    alert(`Tabla ${tabla} agregada correctamente.`);
    location.reload();
  } else {
    console.log("ha ocurrido un error en la solicitud.");
    console.log("Datos sin stringify");
    console.log({ datos: dato });
    console.log("Datos con stringify ---->");
    console.log(JSON.stringify({ datos: dato }));
  }
}

function eventoBotonBorrar() {
  let btnClientes = document.getElementById("borrarClientes");
  btnClientes.addEventListener("click", botonBorrarTabla);

  let btnMarcas = document.getElementById("borrarMarcas");
  btnMarcas.addEventListener("click", botonBorrarTabla);

  let btnServicios = document.getElementById("borrarServicios");
  btnServicios.addEventListener("click", botonBorrarTabla);

  let btnTipos = document.getElementById("borrarTipos");
  btnTipos.addEventListener("click", botonBorrarTabla);

  let btnModelos = document.getElementById("borrarModelos");
  btnModelos.addEventListener("click", botonBorrarTabla);
}

async function botonBorrarTabla(e) {
  e.preventDefault();
  let URL = "";
  let tabla = "";

  if (e.target.id === "borrarClientes") {
    URL = "vaciar/clientes";
    tabla = "Clientes";
  }
  if (e.target.id === "borrarMarcas") {
    URL = "vaciar/marcas";
    tabla = "Marcas";
  }
  if (e.target.id === "borrarServicios") {
    URL = "vaciar/servicios";
    tabla = "Servicios";
  }
  if (e.target.id === "borrarTipos") {
    URL = "vaciar/tipo-equipos";
    tabla = "Tipo de equipos";
  }

  if (e.target.id === "borrarModelos") {
    URL = "vaciar/modelos";
    tabla = "Modelos";
  }

  const vaciar = await fetch(URL, {
    method: "DELETE",
  });

  if (vaciar.ok) {
    console.log("Se eliminó la tabla correctamente.");
    alert(`Tabla ${tabla} eliminada correctamente.`);
    location.reload();
  } else {
    console.log("ha ocurrido un error en la solicitud.");
  }
}

/* function eventoDescargarCsv() {
  let btnDescarga = document.getElementById("descargarCsv");
  btnDescarga.addEventListener("click", (e)=>solicitudDescarga(e));
 // console.log("soft cargó evento descarga csv");
} */
async function solicitudDescarga(e) {
 // e.preventDefault();

  var contentDispo = "";

  await fetch("descargarListado", { method: "GET" })
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

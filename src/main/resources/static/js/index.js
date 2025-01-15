//Con este código puedo ver en qué página me encuentro actualmente:
//var paginaActual = window.location.pathname;
//console.log("" + paginaActual);
var losEquiposTipoequipo = [];
var losEquiposModelo = [];
var losEquiposMarca = [];
var losEquiposServicio = [];
var losEquiposPropiedad = [];
var losEquiposEstado = [];

var contadorPalabrasTipos = 0;
var contadorPalabrasMarca = 0;
var contadorPalabrasServicio = 0;

eventoDescargarCsv();
eventoFormulario();
cargarListas();

function eliminarRepetidos(array) {
  // Usar un Set para eliminar duplicados
  const conjuntoUnico = new Set(array);

  // Convertir el Set de nuevo a un array
  return Array.from(conjuntoUnico);
}

function eventoFormulario() {
  let miform = document.getElementById("form-ingreso-equipo");
  miform.addEventListener("submit", registrarEquipo);
}

async function registrarEquipo(e) {
  console.log("cargo funct registrarEquipo");
  e.preventDefault();

  let cliente = document.getElementById("form-cliente").value;
  let clienteidOption = document.getElementsByName(cliente)[0].id;

  let datos = {};
  datos.cliente = cliente;
  datos.propiedad = document.getElementById("form-propiedad").value;
  datos.serie = document.getElementById("form-nroserie").value;
  datos.tipo_equipo = document.getElementById("form-tipoequipo").value;
  datos.marca = document.getElementById("form-marca").value;
  datos.modelo = document.getElementById("form-modelo").value;
  datos.servicio = document.getElementById("form-servicio").value;
  datos.ubicacion = document.getElementById("form-ubicacion").value;
  datos.clienteide = clienteidOption;
  //let auxEstado = document.getElementById("form-estado").value;

  const tipo_encontrado = losEquiposTipoequipo.find(
    (equip) => equip[1] === datos.tipo_equipo
  );
  const modelo_encontrado = losEquiposModelo.find(
    (equip) => equip[1] === datos.modelo
  );
  const marca_encontrado = losEquiposMarca.find(
    (equip) => equip[1] === datos.marca
  );
  const servicio_encontrado = losEquiposServicio.find(
    (equip) => equip[1] === datos.servicio
  );
  //const estado_encontrado = losEquiposEstado.find(equip => equip.nombre === auxEstado);
  //const propiedad_encontrado = losEquiposPropiedad.find(equip => equip.nombre === datos.propiedad);

  datos.marcaide = marca_encontrado ? marca_encontrado[2] : null;
  datos.modeloide = modelo_encontrado ? modelo_encontrado[2] : null;
  datos.tipoequipoide = tipo_encontrado ? tipo_encontrado[2] : null;
  datos.servicioide = servicio_encontrado ? servicio_encontrado[2] : null;
  //datos.estadoide = estado_encontrado? estado_encontrado.id : null;
  //datos.propiedadide = propiedad_encontrado? propiedad_encontrado.id : null;
  let auxEstado = document.getElementById("form-estado").value;
  let auxPropiedad = document.getElementById("form-propiedad").value;
  datos.estadoide = document.getElementsByName(auxEstado)[0].id;
  datos.propiedadide = document.getElementsByName(auxPropiedad)[0].id;

  if (
    datos.serie !== "/" &&
    datos.serie !== "," &&
    datos.modelo !== "/" &&
    datos.modelo !== ","
  ) {

    if (datos.marcaide == null)
      return alert("Revisar MARCA y reintentar. No se encuentra o es nulo.");
    if (datos.modeloide == null)
      return alert("Revisar MODELO y reintentar. No se encuentra o es nulo.");
    if (datos.tipoequipoide == null)
      return alert(
        "Revisar TIPOEQUIPO y reintentar. No se encuentra o es nulo."
      );
    if (datos.servicioide == null)
      return alert("Revisar SERVICIO y reintentar. No se encuentra o es nulo.");

    const resp = await fetch("ingresar-equipo", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(datos),
    });
    if (resp.ok) {
      alert(datos.serie + " cargado con éxito!");

      //Borrar algunos campos para hacer más rápida la carga de datos:
      document.getElementById("form-nroserie").value = "";
      document.getElementById("form-ubicacion").value = "";
    } else {
      console.log("Error, datos enviados: ");
      console.log(datos);
      alert(
        "Error al cargar equipo en el servidor. Contactar al administrador."
      );
    }
  } else {
    alert(
      "No se cargó el equipo \n porque contiene ',' o '/' \n arreglarlo y reintentar."
    );
  }
}

async function cargarListas() {
  /* Cargo las opciones para cada listado: */

  //Cliente:  <--------------
  let clientes = await cargarInfo("clientes");

  if (clientes) {
    let menuCliente = document.getElementById("form-cliente");

    clientes.forEach((el) => {
      let opti = document.createElement("option");
      opti.value = String(el[1]);
      opti.innerText = String(el[1]);
      opti.id = String(el[2]);
      opti.setAttribute("name", String(el[1]));
      menuCliente.append(opti);
    });
  } else {
    console.log("No hay clientes para cargar.");
  }

  //Tipo de equipo  <----------

  //obtengo datos del server
  let tipoEquipos = await cargarInfo("tipoEquipos");

  if (tipoEquipos) {
    losEquiposTipoequipo = tipoEquipos;
    // losEquiposTipoequipo -> [0] = ID , [1] = nombre.

    /* //Lo paso a un array de Strings
    let auxiliarTipos = [];
    tipoEquipos.forEach((eqs) => {
      auxiliarTipos.push(String(eqs[1]));

      losEquiposTipoequipo = eliminarRepetidos(auxiliarTipos);
    }); */

    let listadoPredictivo = document.getElementById(
      "predictive-list-tipoequipo"
    );
    listadoPredictivo.style.display = "none";
    let tipoequipoInput = document.getElementById("form-tipoequipo");
    tipoequipoInput.addEventListener("input", predictivoTipoEquipo);
  } else {
    console.log("No hay tipos de equipos para cargar.");
  }

  //Marca  <---------------
  let marcas = await cargarInfo("marcas");

  if (marcas) {
    losEquiposMarca = marcas;
    /*  let auxMarcas = [];
    marcas.forEach((eqs) => {
      auxMarcas.push(String(eqs[1]));
      losEquiposMarca = eliminarRepetidos(auxMarcas);
    }); */

    let listadoPredictivo = document.getElementById("predictive-list-marca");
    listadoPredictivo.style.display = "none";
    let marcaInput = document.getElementById("form-marca");
    marcaInput.addEventListener("input", predictivoMarca);
  } else {
    console.log("No hay marcas para cargar.");
  }

  //Servicio  <------------
  let servicios = await cargarInfo("servicios");

  if (servicios) {
    losEquiposServicio = servicios;
    /* let auxServicios = [];
    servicios.forEach((eqs) => {
      auxServicios.push(String(eqs[1]));
      losEquiposServicio = eliminarRepetidos(auxServicios);
    }); */

    let listadoPredictivo = document.getElementById("predictive-list-servicio");
    listadoPredictivo.style.display = "none";
    let servicioInput = document.getElementById("form-servicio");
    servicioInput.addEventListener("input", predictivoServicio);
  } else {
    console.log("No hay servicios para cargar.");
  }

  //Modelo   <----------------
  let modelos = await cargarInfo("modelos");

  if (modelos) {
    losEquiposModelo = modelos;
    /* modelos.forEach((eqs) => {
      losEquiposModelo.push(String(eqs[1]));
    }); */

    let listadoPredictivo = document.getElementById("predictive-list-modelo");
    listadoPredictivo.style.display = "none";
    let menuModelos = document.getElementById("form-modelo");
    menuModelos.addEventListener("input", predictivoModelo);
  } else {
    console.log("No hay modelos para cargar.");
  }

  //Estado:  <--------------
  let estados = await cargarInfo("estados");

  if (estados) {
    let menuEstado = document.getElementById("form-estado");
    // 0-> id, 1-> nombre, 2-> id server
    estados.forEach((el) => {
      let opti = document.createElement("option");
      opti.value = String(el[1]);
      opti.innerText = String(el[1]).toLowerCase();
      opti.id = String(el[2]);
      opti.setAttribute("name", String(el[1]));
      if (el[1].toLowerCase() == "operativo") {
        opti.selected;
      }
      menuEstado.append(opti);
    });
  } else {
    console.log("No hay estados para cargar.");
  }

  //Propiedades:  <--------------
  let propiedades = await cargarInfo("propiedades");

  if (propiedades) {
    let menuPropiedades = document.getElementById("form-propiedad");
    // 0-> id, 1-> nombre, 2-> id server
    propiedades.forEach((el) => {
      let opti = document.createElement("option");
      opti.value = String(el[1]);
      opti.innerText = String(el[1]).toLowerCase();
      opti.id = String(el[2]);
      opti.setAttribute("name", String(el[1]));
      if (el[1].toLowerCase() == "propio") {
        opti.selected;
      }
      menuPropiedades.append(opti);
    });
  } else {
    console.log("No hay propiedades para cargar.");
  }
}

async function cargarInfo(tabla) {
  url = tabla;
  const res = await fetch(url, {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  });
  const data = res.json();
  return data;
}

function eventoDescargarCsv() {
  let btnDescarga = document.getElementById("descargarCsv");
  btnDescarga.addEventListener("click", solicitudDescarga);
  //console.log("cargó evento descarga");
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

function predictivoTipoEquipo() {
  let tipoequipoInput = document.getElementById("form-tipoequipo");
  let entradaInput = tipoequipoInput.value.toUpperCase();
  let listadoPredictivo = document.getElementById("predictive-list-tipoequipo");
  listadoPredictivo.innerHTML = "";

  losEquiposTipoequipo.forEach((palabra) => {
    /* Indico que palabra[1] está el nombre
    y palabra[0] el ID.
    */

    // Solo procesar si hay texto en la entrada
    if (entradaInput.length === 0) {
      listadoPredictivo.style.display = "none";
      return;
    }
    let contadorPalabrasTipos = 0;

    if (contadorPalabrasTipos >= 300) {
      return; //Detengo el procesamiento de datos.
    }

    if (palabra[1].toUpperCase().startsWith(entradaInput)) {
      let listItem = document.createElement("li");
      listItem.style.width = String(tipoequipoInput.offsetWidth + "px");
      listadoPredictivo.style.width = String(
        tipoequipoInput.offsetWidth + "px"
      );
      listItem.textContent = palabra[1];
      listItem.addEventListener("click", () => {
        tipoequipoInput.value = listItem.textContent;
        listadoPredictivo.style.display = "none";
      });
      listadoPredictivo.appendChild(listItem);
      contadorPalabrasTipos++;
    }
  });

  if (entradaInput.trim() == "") {
    listadoPredictivo.style.display = "none";
  } else {
    listadoPredictivo.style.display = "block";
  }
}

function predictivoModelo() {
  let modeloInput = document.getElementById("form-modelo");
  let entradaInput = modeloInput.value.toUpperCase();
  let listadoPredictivo = document.getElementById("predictive-list-modelo");
  listadoPredictivo.innerHTML = "";

  losEquiposModelo.forEach((palabra) => {
    // Solo procesar si hay texto en la entrada
    if (entradaInput.length === 0) {
      listadoPredictivo.style.display = "none";
      return;
    }
    let contadorPalabrasModelo = 0;

    if (contadorPalabrasModelo >= 300) {
      return; //Detengo el procesamiento de datos.
    }

    if (palabra[1].toUpperCase().startsWith(entradaInput)) {
      let listItem = document.createElement("li");
      listItem.style.width = String(modeloInput.offsetWidth + "px");
      listadoPredictivo.style.width = String(modeloInput.offsetWidth + "px");
      listItem.textContent = palabra[1];
      listItem.addEventListener("click", () => {
        modeloInput.value = listItem.textContent;
        listadoPredictivo.style.display = "none";
      });
      listadoPredictivo.appendChild(listItem);
      contadorPalabrasModelo++;
    }
  });

  if (entradaInput.trim() == "") {
    listadoPredictivo.style.display = "none";
  } else {
    listadoPredictivo.style.display = "block";
  }
}

function predictivoMarca() {
  let marcaInput = document.getElementById("form-marca");
  let entradaInput = marcaInput.value.toUpperCase();
  let listadoPredictivo = document.getElementById("predictive-list-marca");
  listadoPredictivo.innerHTML = "";

  losEquiposMarca.forEach((palabra) => {
    // Solo procesar si hay texto en la entrada
    if (entradaInput.length === 0) {
      listadoPredictivo.style.display = "none";
      return;
    }
    let contadorPalabrasMarca = 0;

    if (contadorPalabrasMarca >= 300) {
      return; //Detengo el procesamiento de datos.
    }

    if (palabra[1].toUpperCase().startsWith(entradaInput)) {
      let listItem = document.createElement("li");
      listItem.style.width = String(marcaInput.offsetWidth + "px");
      listadoPredictivo.style.width = String(marcaInput.offsetWidth + "px");
      listItem.textContent = palabra[1];
      listItem.addEventListener("click", () => {
        marcaInput.value = listItem.textContent;
        listadoPredictivo.style.display = "none";
      });
      listadoPredictivo.appendChild(listItem);
      contadorPalabrasMarca++;
    }
  });

  if (entradaInput.trim() == "") {
    listadoPredictivo.style.display = "none";
  } else {
    listadoPredictivo.style.display = "block";
  }
}

function predictivoServicio() {
  let servicioInput = document.getElementById("form-servicio");
  let entradaInput = servicioInput.value.toUpperCase();
  let listadoPredictivo = document.getElementById("predictive-list-servicio");
  listadoPredictivo.innerHTML = "";

  losEquiposServicio.forEach((palabra) => {
    // Solo procesar si hay texto en la entrada
    if (entradaInput.length === 0) {
      listadoPredictivo.style.display = "none";
      return;
    }
    let contadorPalabrasServicio = 0;

    if (contadorPalabrasServicio >= 300) {
      return; //Detengo el procesamiento de datos.
    }

    if (palabra[1].toUpperCase().startsWith(entradaInput)) {
      let listItem = document.createElement("li");
      listItem.style.width = String(servicioInput.offsetWidth + "px");
      listadoPredictivo.style.width = String(servicioInput.offsetWidth + "px");
      listItem.textContent = palabra[1];
      listItem.addEventListener("click", () => {
        servicioInput.value = listItem.textContent;
        listadoPredictivo.style.display = "none";
      });
      listadoPredictivo.appendChild(listItem);
      contadorPalabrasServicio++;
    }
  });

  if (entradaInput.trim() == "") {
    listadoPredictivo.style.display = "none";
  } else {
    listadoPredictivo.style.display = "block";
  }
}

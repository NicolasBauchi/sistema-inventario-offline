

//Con este código puedo ver en qué página me encuentro actualmente:
//var paginaActual = window.location.pathname;
//console.log("" + paginaActual);
eventoDescargarCsv();

eventoFormulario();
cargarListas();



/* Seccion registro de equipo */
function eventoFormulario() {

    let miform = document.getElementById("form-ingreso-equipo");
    miform.addEventListener("submit", registrarEquipo);
}

async function registrarEquipo(e) {
    console.log("cargo funct registrarEquipo");
    e.preventDefault();
    let datos = {};
    datos.cliente = document.getElementById("form-cliente").value;
    datos.propiedad = document.getElementById("form-propiedad").value;
    datos.serie = document.getElementById("form-nroserie").value;
    datos.tipo_equipo = document.getElementById("form-tipoequipo").value;
    datos.marca = document.getElementById("form-marca").value;
    datos.modelo = document.getElementById("form-modelo").value;
    datos.servicio = document.getElementById("form-servicio").value;
    datos.ubicacion = document.getElementById("form-ubicacion").value;

    if (datos.serie !== "\/" && datos.serie !== "," && datos.modelo !== "\/" && datos.modelo !== ",") {
        const resp = await fetch('ingresar-equipo', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        })
        if (resp.ok) {
            alert(datos.serie + " cargado con éxito!");

            //Borrar algunos campos para hacer más rápida la carga de datos:
            document.getElementById("form-nroserie").value = "";
            document.getElementById("form-ubicacion").value = "";


        } else {
            alert("Error al cargar equipo en el servidor. Contactar al administrador.");
        };
    } else {
        alert("No se cargó el equipo \n porque contiene ',' o '/' \n arreglarlo y reintentar.");
    }


}

async function cargarListas() {
    /* Cargo las opciones para cada listado: */

    //Cliente:
    let clientes = await cargarInfo("clientes");

    if (clientes) {
        let menuCliente = document.getElementById("form-cliente");

        clientes.forEach(el => {
            let opti = document.createElement("option");
            opti.value = String(el[1]);
            opti.innerText = String(el[1]);
            menuCliente.append(opti);
        });
    } else {
        console.log("No hay clientes para cargar.");
    }

    //Tipo de equipo
    let tipoEquipos = await cargarInfo("tipoEquipos");

    if (tipoEquipos) {
        let menuTipoEquipos = document.getElementById("form-tipoequipo");

        tipoEquipos.forEach(el => {
            let opti = document.createElement("option");
            /* opti.value = String(el.nombre); */
            opti.value = String(el[1]);
            opti.innerText = String(el[1]);
            menuTipoEquipos.appendChild(opti);
        });
    } else {
        console.log("No hay tipos de equipos para cargar.");
    }

    //Marca
    let marcas = await cargarInfo("marcas");

    if (marcas) {
        let menuMarcas = document.getElementById("form-marca");

        marcas.forEach(el => {
            let opti = document.createElement("option");
            opti.value = String(el[1]);
            opti.innerText = String(el[1]);
            menuMarcas.appendChild(opti);
        });
    } else {
        console.log("No hay marcas para cargar.");
    }


    //Servicio
    let servicios = await cargarInfo("servicios");

    if (servicios) {
        let menuServicio = document.getElementById("form-servicio");

        servicios.forEach(el => {
            let opti = document.createElement("option");
            opti.value = String(el[1]);
            opti.innerText = String(el[1]);
            menuServicio.appendChild(opti);
        });
    } else {
        console.log("No hay servicios para cargar.");
    }

}

async function cargarInfo(tabla) {

    url = tabla;
    const res = await fetch(url, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const data = res.json();
    return data;

}

function eventoDescargarCsv() {
    let btnDescarga = document.getElementById("descargarCsv");
    btnDescarga.addEventListener("click", solicitudDescarga);
    console.log("cargó evento descarga");
}
async function solicitudDescarga(e) {
    e.preventDefault();
    var contentDispo = "";

    fetch('descargarListado', { method: "POST" })
        .then(response => {
            contentDispo = response.headers.get("Content-Disposition");


            return response.blob();
        })
        .then(blob => {

            // Crea un objeto URL para el blob
            const url = window.URL.createObjectURL(blob);

            // Crea un elemento de enlace temporal y lo agrega al DOM
            const a = document.createElement('a');
            a.style.display = 'none';
            a.href = url;
            a.download = contentDispo.split('filename=')[1];;
            document.body.appendChild(a);

            // Simula un clic en el elemento de enlace para iniciar la descarga
            a.click();

            // Elimina el elemento de enlace y libera el objeto URL
            document.body.removeChild(a);
            window.URL.revokeObjectURL(url);
        });
}
const URL_API = 'http://localhost:8080/api';
let carritoCompras = [];
let cacheVariantes = [];
let cacheMuebles = [];

document.addEventListener('DOMContentLoaded', () => {
    cargarDatosAdministrador();
});

function cambiarVista(nombreVista) {
    const contenedorAdmin = document.getElementById('vista-admin');
    const contenedorCliente = document.getElementById('vista-cliente');

    if (nombreVista === 'admin') {
        contenedorAdmin.style.display = 'block';
        contenedorCliente.style.display = 'none';
        cargarDatosAdministrador();
    } else {
        contenedorAdmin.style.display = 'none';
        contenedorCliente.style.display = 'block';
        cargarCatalogoCliente();
    }
}

async function cargarDatosAdministrador() {
    await obtenerVariantes();
    await obtenerMueblesAdmin();
}

async function obtenerMueblesAdmin() {
    try {
        const respuesta = await fetch(`${URL_API}/muebles`);
        cacheMuebles = await respuesta.json();
        renderizarTablaAdmin(cacheMuebles);
    } catch (error) { console.error(error); }
}

async function obtenerVariantes() {
    try {
        const respuesta = await fetch(`${URL_API}/variantes`);
        cacheVariantes = await respuesta.json();
        renderizarTablaVariantes(cacheVariantes);
    } catch (error) { console.error(error); }
}

function renderizarTablaAdmin(listaMuebles) {
    const cuerpoTabla = document.querySelector('#tablaAdminMuebles tbody');
    cuerpoTabla.innerHTML = '';
    listaMuebles.forEach(mueble => {
        const fila = document.createElement('tr');
        fila.innerHTML = `
            <td>${mueble.id}</td>
            <td>${mueble.nombre}</td>
            <td>${mueble.stock}</td>
            <td>${mueble.estado ? 'Activo' : 'Inactivo'}</td>
            <td>
                <button onclick="cambiarEstadoMueble(${mueble.id}, ${!mueble.estado})" 
                    class="${mueble.estado ? 'boton-desactivar' : 'boton-activar'}">
                    ${mueble.estado ? 'Desactivar' : 'Activar'}
                </button>
            </td>
        `;
        cuerpoTabla.appendChild(fila);
    });
}

function renderizarTablaVariantes(listaVariantes) {
    const cuerpoTabla = document.querySelector('#tablaAdminVariantes tbody');
    cuerpoTabla.innerHTML = '';
    listaVariantes.forEach(variante => {
        const fila = document.createElement('tr');
        fila.innerHTML = `
            <td>${variante.id}</td>
            <td>${variante.nombre}</td>
            <td>$${variante.aumentoPrecio}</td>
            <td>-</td>
        `;
        cuerpoTabla.appendChild(fila);
    });
}

document.getElementById('formularioMueble').addEventListener('submit', async (evento) => {
    evento.preventDefault();
    const valorPrecio = parseInt(document.getElementById('precioMueble').value);
    const valorStock = parseInt(document.getElementById('stockMueble').value);

    if (valorPrecio < 0 || valorStock < 0) {
        alert("El precio y el stock no pueden ser negativos");
        return;
    }

    const nuevoMueble = {
        nombre: document.getElementById('nombreMueble').value,
        tipo: document.getElementById('tipoMueble').value,
        precioBase: valorPrecio,
        stock: valorStock,
        tamanio: document.getElementById('tamanoMueble').value,
        material: document.getElementById('materialMueble').value,
        estado: true
    };

    await fetch(`${URL_API}/muebles`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(nuevoMueble)
    });

    document.getElementById('formularioMueble').reset();
    obtenerMueblesAdmin();
    alert("Mueble creado correctamente");
});

document.getElementById('formularioVariante').addEventListener('submit', async (evento) => {
    evento.preventDefault();
    const valorAumento = parseInt(document.getElementById('precioVariante').value);

    if (valorAumento < 0) {
        alert("El precio no puede ser negativo");
        return;
    }

    const nuevaVariante = {
        nombre: document.getElementById('nombreVariante').value,
        descripcion: document.getElementById('descripcionVariante').value,
        aumentoPrecio: valorAumento
    };

    await fetch(`${URL_API}/variantes`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(nuevaVariante)
    });

    document.getElementById('formularioVariante').reset();
    obtenerVariantes();
    alert("Variante creada correctamente");
});

async function cambiarEstadoMueble(idMueble, nuevoEstado) {
    const muebleEncontrado = cacheMuebles.find(m => m.id === idMueble);
    if (!muebleEncontrado) return;

    muebleEncontrado.estado = nuevoEstado;

    await fetch(`${URL_API}/muebles/${idMueble}`, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(muebleEncontrado)
    });
    obtenerMueblesAdmin();
}

async function cargarCatalogoCliente() {
    await obtenerVariantes();
    const respuesta = await fetch(`${URL_API}/muebles/estado/true`);
    const mueblesActivos = await respuesta.json();
    cacheMuebles = mueblesActivos;
    renderizarCatalogoCliente(mueblesActivos);
}

function renderizarCatalogoCliente(listaMuebles) {
    const contenedorGrid = document.getElementById('cuadriculaProductos');
    contenedorGrid.innerHTML = '';

    listaMuebles.forEach(mueble => {
        let opcionesVariantes = `<option value="">Sin variante (Precio Base)</option>`;
        cacheVariantes.forEach(variante => {
            opcionesVariantes += `<option value="${variante.id}">${variante.nombre} (+$${variante.aumentoPrecio})</option>`;
        });

        const tarjeta = document.createElement('div');
        tarjeta.className = 'tarjeta-producto';
        tarjeta.innerHTML = `
            <h3>${mueble.nombre}</h3>
            <p>${mueble.material} - ${mueble.tamanio}</p>
            <p class="texto-precio">Base: $${mueble.precioBase}</p>
            <p class="texto-stock">Disponibles: ${mueble.stock}</p>
            
            <label>Variante:</label>
            <select id="sel-var-${mueble.id}">${opcionesVariantes}</select>
            
            <label>Cantidad:</label>
            <input type="number" id="inp-cant-${mueble.id}" value="1" min="1" max="${mueble.stock}">
            
            <button onclick="agregarAlCarrito(${mueble.id})">Agregar a Cotización</button>
        `;
        contenedorGrid.appendChild(tarjeta);
    });
}

function agregarAlCarrito(idMueble) {
    const muebleSeleccionado = cacheMuebles.find(m => m.id === idMueble);
    const idVarianteSeleccionada = document.getElementById(`sel-var-${idMueble}`).value;
    const cantidadIngresada = parseInt(document.getElementById(`inp-cant-${idMueble}`).value);

    if (cantidadIngresada <= 0) {
        alert("La cantidad debe ser mayor a 0");
        return;
    }
    if (cantidadIngresada > muebleSeleccionado.stock) {
        alert(`No puedes agregar más del stock disponible (${muebleSeleccionado.stock})`);
        return;
    }

    const cantidadEnCarrito = carritoCompras.filter(item => item.mueble.id === idMueble)
        .reduce((suma, item) => suma + item.cantidad, 0);

    if (cantidadEnCarrito + cantidadIngresada > muebleSeleccionado.stock) {
        alert("Stock insuficiente para agregar más unidades de este producto.");
        return;
    }

    let objetoVariante = null;
    let precioCalculado = muebleSeleccionado.precioBase;

    if (idVarianteSeleccionada) {
        objetoVariante = cacheVariantes.find(v => v.id == idVarianteSeleccionada);
        precioCalculado += objetoVariante.aumentoPrecio;
    }

    const itemCarrito = {
        mueble: muebleSeleccionado,
        variante: objetoVariante,
        cantidad: cantidadIngresada,
        precioUnitario: precioCalculado,
        subtotal: precioCalculado * cantidadIngresada
    };

    carritoCompras.push(itemCarrito);
    renderizarCarrito();
}

function renderizarCarrito() {
    const contenedorLista = document.getElementById('listaCotizacion');
    const spanTotal = document.getElementById('totalCotizacion');
    contenedorLista.innerHTML = '';

    let sumaTotal = 0;

    carritoCompras.forEach((item, indice) => {
        sumaTotal += item.subtotal;
        const nombreVariante = item.variante ? item.variante.nombre : "Normal";

        const divItem = document.createElement('div');
        divItem.className = 'item-carrito';
        divItem.innerHTML = `
            <div>
                <strong>${item.mueble.nombre}</strong> (${nombreVariante})<br>
                Cant: ${item.cantidad} x $${item.precioUnitario}
            </div>
            <div>
                $${item.subtotal}
                <button onclick="eliminarDelCarrito(${indice})" style="background:red; width:auto; padding:2px 8px;">X</button>
            </div>
        `;
        contenedorLista.appendChild(divItem);
    });

    spanTotal.innerText = sumaTotal;
}

function eliminarDelCarrito(indice) {
    carritoCompras.splice(indice, 1);
    renderizarCarrito();
}

async function confirmarVenta() {
    if (carritoCompras.length === 0) {
        alert("La cotización está vacía.");
        return;
    }

    const totalFinal = parseInt(document.getElementById('totalCotizacion').innerText);
    const nombreClienteDefecto = "Cliente Mostrador";
    const metodoPagoDefecto = "Efectivo";

    const datosCotizacion = {
        fecha: new Date().toISOString(),
        cliente: nombreClienteDefecto,
        total: totalFinal,
        estado: "Pendiente",
        detalles: carritoCompras.map(item => ({
            mueble: { id: item.mueble.id },
            variante: item.variante ? { id: item.variante.id } : null,
            cantidad: item.cantidad,
            precioUnitario: item.precioUnitario,
            subtotal: item.subtotal
        }))
    };

    try {
        const respuestaCotizacion = await fetch(`${URL_API}/cotizaciones`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(datosCotizacion)
        });

        if (!respuestaCotizacion.ok) throw new Error("Error creando cotización");
        const cotizacionCreada = await respuestaCotizacion.json();

        const datosVenta = {
            cotizacion: { id: cotizacionCreada.id },
            fecha: new Date().toISOString(),
            metodoPago: metodoPagoDefecto,
            total: totalFinal,
            estado: "Confirmada"
        };

        const respuestaVenta = await fetch(`${URL_API}/ventas`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(datosVenta)
        });

        if (respuestaVenta.ok) {
            await actualizarStockBackend();

            alert(`¡Venta confirmada! Se ha descontado el stock.`);
            carritoCompras = [];
            renderizarCarrito();
            cargarCatalogoCliente();
        } else {
            alert("Error al confirmar la venta final.");
        }

    } catch (error) {
        console.error(error);
        alert("Ocurrió un error en el proceso de compra.");
    }
}

async function actualizarStockBackend() {
    for (const item of carritoCompras) {
        const muebleAActualizar = item.mueble;
        const nuevoStock = muebleAActualizar.stock - item.cantidad;

        muebleAActualizar.stock = nuevoStock;

        try {
            await fetch(`${URL_API}/muebles/${muebleAActualizar.id}`, {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(muebleAActualizar)
            });
        } catch (error) {
            console.error(`Error actualizando stock del mueble ${muebleAActualizar.id}`, error);
        }
    }
}
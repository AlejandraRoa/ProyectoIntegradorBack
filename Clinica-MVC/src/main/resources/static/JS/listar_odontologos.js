const tableBody = document.querySelector("#odontologosTable tbody");

function fetchOdontologos() {
    fetch(`/odontologos`)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            tableBody.innerHTML = "";

            data.forEach((odontologo) => {
                const row = document.createElement("tr");
                row.innerHTML = `
                <td>${odontologo.id}</td>
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
                <td>${odontologo.numeroMatricula}</td>
                <td>
                  <button class="btn btn-primary btn-sm" onclick="editOdontologo(${odontologo.id}, '${odontologo.nombre}', '${odontologo.apellido}', '${odontologo.numeroMatricula}')">Modificar</button>
                  <button class="btn btn-danger btn-sm" onclick="deleteOdontologo(${odontologo.id})">Eliminar</button>
                </td>
              `;
                tableBody.appendChild(row);
            });
        })
        .catch((error) => {
            console.error("Error fetching data:", error);
        });
}

function deleteOdontologo(id) {
    fetch(`/odontologos/${id}`, {
            method: "DELETE",
        })
        .then((response) => {
            if (response.ok) {
                console.log(`Odontologo con ID ${id} eliminado`);
                fetchOdontologos();
            } else {
                console.error("Error al eliminar el odontólogo");
            }
        })
        .catch((error) => {
            console.error("Error en la solicitud de eliminación:", error);
        });
}

function editOdontologo(id, nombre, apellido, numeroMatricula) {
    document.getElementById("editId").value = id;
    document.getElementById("editNombre").value = nombre;
    document.getElementById("editApellido").value = apellido;
    document.getElementById("editNumeroMatricula").value = numeroMatricula;
    document.getElementById("editOdontologoForm").style.display = "block";
}

function submitEdit() {
    const id = document.getElementById("editId").value;
    const nombre = document.getElementById("editNombre").value;
    const apellido = document.getElementById("editApellido").value;
    const numeroMatricula = document.getElementById("editNumeroMatricula").value;

    const updatedOdontologo = {
        id: id,
        nombre: nombre,
        apellido: apellido,
        numeroMatricula: numeroMatricula
    };

    fetch(`/odontologos/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(updatedOdontologo),
    })
    .then((response) => {
        if (response.ok) {
            console.log("Odontólogo actualizado");
            alert("Odontólogo modificado con éxito");
            fetchOdontologos();
            document.getElementById("editOdontologoForm").style.display = "none";
        } else {
            alert("Error al actualizar el odontólogo");
        }
    })
    .catch((error) => {
        console.error("Error en la solicitud de actualización:", error);
    });
}

fetchOdontologos();
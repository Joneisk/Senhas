document.addEventListener("DOMContentLoaded", function() {
    // ...

    document.getElementById("add-cargo").addEventListener("click", function() {
        console.log("Botão Add Cargo clicado!");
        document.getElementById("modal-add-cargo").style.display = "block";
    });

    document.getElementById("form-add-cargo").addEventListener("submit", function(event) {
        event.preventDefault();
        var nome = document.getElementById("nome").value;
        var nivel = document.getElementById("nivel").value;
        var cargo = {
            "nome": nome,
            "nivel": nivel
        };

        // Enviar os dados para o servidor
        fetch("http://localhost:8080/apis/admin/add-cargo", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(cargo)
        })
            .then(response => {
                console.log("Resposta do servidor:", response);
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error(`Erro ao adicionar cargo: ${response.status}`);
                }
            })
            .then(data => {
                console.log("Dados retornados pelo servidor:", data);
                // Remover a condição de sucesso, pois o servidor retorna o objeto Cargo
                document.getElementById("mensagem").innerHTML = `
          <h2>Cargo adicionado com sucesso!</h2>
          <p>O cargo ${nome} foi adicionado com sucesso.</p>
        `;
            })
            .catch(error => {
                console.log("Erro capturado:", error);
                console.log("Response:", error.response);
                // Mostrar mensagem de erro
                document.getElementById("mensagem").innerHTML = `
          <h2>Erro ao adicionar cargo</h2>
          <p>Ocorreu um erro ao adicionar o cargo ${nome}. Tente novamente.</p>
        `;
            });
    });
});
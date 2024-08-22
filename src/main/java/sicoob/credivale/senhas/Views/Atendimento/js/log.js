document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("loginForm").addEventListener("submit", async function(event) {
        event.preventDefault(); // Previne o envio padrão do formulário

        var email = document.getElementById("email").value;
        var senha = document.getElementById("password").value;

        var user = {
            email: email,
            senha: senha
        };

        const url = 'http://localhost:8080/apis/login/login';
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(user)
            });

            if (response.ok) {
                const token = await response.text();

                try {
                    const payload = JSON.parse(atob(token.split('.')[1]));
                    const nivel = parseInt(payload.nivel);

                    // Armazenar o token e o email no localStorage
                    localStorage.setItem('token', token);
                    localStorage.setItem('email', email); // Armazena o email

                    // Fetch the user ID using the new endpoint
                    const userIdResponse = await fetch(`http://localhost:8080/apis/login/get-atendente?email=${email}`);
                    if (userIdResponse.ok) {
                        const userId = await userIdResponse.json();
                        localStorage.setItem('userId', userId); // Armazena o userId

                        if (nivel === 1) {
                            window.location.href = "../html/index.html";
                        } else if (nivel === 2) {
                            window.location.href = "../html/index.html";
                        } else {
                            alert('Nível de usuário desconhecido.');
                        }
                    } else {
                        alert("Erro ao obter ID do usuário.");
                    }
                } catch (error) {
                    console.error('Error decoding token:', error);
                    alert("Erro ao decodificar token.");
                }
            } else {
                const errorMessage = await response.text();
                alert(`Erro ao fazer login: ${errorMessage}`);
            }
        } catch (error) {
            console.error('Error:', error);
            alert("An error occurred. Please try again later.");
        }
    });
});
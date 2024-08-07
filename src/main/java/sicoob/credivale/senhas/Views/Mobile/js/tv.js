let tipoAtendimento = '';
let valor = 0;

// Função para pegar o parâmetro tipo da URL
function getTipoAtendimento() {
    const urlParams = new URLSearchParams(window.location.search);
    tipoAtendimento = urlParams.get('tipo');
}

// Função para somar valor
function somaValor(opcao) {
    getTipoAtendimento();
    switch (tipoAtendimento) {
        case 'caixa':
            switch (opcao) {
                case 'normal':
                    valor = 1;
                    break;
                case 'preferencial':
                    valor = 2;
                    break;
                case 'prioritario':
                    valor = 3;
                    break;
            }
            break;
        case 'atendimento':
            switch (opcao) {
                case 'normal':
                    valor = 4;
                    break;
                case 'preferencial':
                    valor = 5;
                    break;
                case 'prioritario':
                    valor = 6;
                    break;
            }
            break;
        case 'gerencia':
            switch (opcao) {
                case 'normal':
                    valor = 7;
                    break;
                case 'preferencial':
                    valor = 8;
                    break;
                case 'prioritario':
                    valor = 9;
                    break;
            }
            break;
    }
    document.getElementById('valor-atual').innerHTML = `Valor atual: ${valor}`;

    const dataEmissao = new Date(); // data e hora atuais
    const dataEmissaoFormatada = dataEmissao.toISOString().split('T')[0]; // formato ISO-8601

    const senhaa = {
        horaEmissao: dataEmissao.toLocaleTimeString(),
        dataEmissao: dataEmissaoFormatada,
        tipoAtendimento: { id: valor },
    };

    // Verificar se o objeto senhaa está correto antes de enviar a requisição
    console.log(senhaa);

    fetch('http://localhost:8080/apis/user/add-senha', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(senhaa),
    })
        .then(response => response.json())
        .then(data => {
            const mensagem = document.getElementById('mensagem');
            mensagem.innerHTML = 'Senha adicionada com sucesso!';
            mensagem.style.display = 'block';
            setTimeout(() => {
                mensagem.style.display = 'none';
                window.location.href = '../html/index.html';
            }, 2000);
        })
        .catch(error => {
            document.getElementById('mensagem').innerHTML = 'Erro ao adicionar senha!';
            console.error(error);
        });
}

// Função para voltar ao menu inicial
function voltarMenu() {
    window.location.href = '../html/index.html';
}
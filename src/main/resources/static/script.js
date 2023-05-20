const url = 'pacientes/';

const paciente = {
  nome: 'Nome do Paciente',
  diaRealizadoAula: '2023-05-19'
};

fetch(url, {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify(paciente)
})
.then(response => response.json())
.then(data => {
  // Manipula a resposta da API
  console.log(data);
})
.catch(error => {
  // Manipula erros
  console.error(error);
});
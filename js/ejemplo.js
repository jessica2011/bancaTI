

// $('#ingresar').click(function(){
//   $.ajax({
//     type: "GET",
//     url: `http://192.168.65.156:8084/peter/OmniFinanceServlet??usuario=${user}`,
//     dataType: "json",
//     data: {
//       usuario: usuarioName
//     },
//     success: function(data){
//       console.log(data.usuario)
//     //     $.each(data, function(i,item){
//     //     $("#busqHidCliente").val(item.n_codigo_cliente); //Setear el campo nombre con el valor obtenido de la funcion ajax y el campo hidden
//     //     $("#busqTxtNombre").val(item.v_nombre_cliente);
//     // });
//     }
//   });
// })





const begin = () =>{

  const userDni = () =>{
    let user = $('#user').val();
    console.log(user);
    $.getJSON(`http://192.168.65.156:8084/peter/OmniFinanceServlet??usuario=${user}`, function(response) {
      console.log(response)
      // let idCategories = [];
      // for (let i in response) {
      //   idCategories = response[i].id;
      //   console.log(idCategories);
      // }
      // response.forEach((elem, i) => {
      //   const templateCategories = `<a href="/${elem.id}" class="list-group-item list-group-item-action">${elem.name}</a>`;
      //   $('#list-categories').append(templateCategories);
      // });
    });
    
  };
  
  $('#ingresar').click( function(event){
    event.preventDefault();
    userDni();
  })


};

$(document).ready(begin);


$(document).ready(function() {
  /* Inicializando select Documento*/
  $('select').material_select();
  /* Variables */
  var $dni = $('#dni');
  var $password = $('#password');
  var $celular = $('#celular');
  var $btnLogin = $('#btnLogin');

  var validateDni = false;
  var validatePassword = false;
  var validateUser = false;
  var validateCelular = false;

  /* Validación de boton activado/desactivado */
  function activeBtn() {
    if (validateDni && validatePassword && validateCelular) {
      $btnLogin.attr('disabled', false);
    }
  }
  function desactiveBtn() {
    $btnLogin.attr('disabled', true);
  }
  $dni.on('input', function(event) {
    if ($dni.val()) {
      validateDni = true;
      activeBtn();
    } else 
      desactiveBtn();
  });

  $password.on('input', function(event) {
    if ($password.val()) {
      validatePassword = true;
      activeBtn();
    } else 
      desactiveBtn();
  });

  $celular.on('input', function(event) {
    if ($celular.val().length ==  9) {
      validateCelular = true;
      activeBtn();
    } else 
      desactiveBtn();
  });


  /* Logeo con Firebase */
  $btnLogin.click(function() {
    firebase.database().ref('user')
      .on('child_added', function(s) {
        var users = s.val();
        /* console.log(users.CU); */
        if (users.codigo === $dni.val() && users.clave === $password.val()) {
          console.log(users.login);
          validateUser = true;
          alert('oh sii')
          // $(location).attr('href', 'home.html');
        }
      });
  });
});

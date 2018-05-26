 /* Logeo con Firebase */
 $btnLogin.click(function() {
    firebase.database().ref('users')
      .on('child_added', function(s) {
        var users = s.val();
        /* console.log(users.CU); */
        if (users.CU === $dni.val() && users.CU === $password.val()) {
          console.log(users.Cliente);
          validateUser = true;
          $(location).attr('href', 'home.html');
        }
      });
  });
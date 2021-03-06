var request;
$(s).submit(function(event){
  if (request) {
    request.abort();
  }
  var $form = $(this);
  var $inputs = $form.find("input, select, button, textarea");
  var serializedData = $form.serialize();
  $inputs.prop("disabled", true);
  request = $.ajax({
      url: "/dbcustomerupdate.php",
      type:"post",
      data: serializedData
  });
  request.done(function (response, textStatus, jqXHR){
    console.log("It worked!");
  });
  request.fail(function (jgXHR, textStatus, errorThrown){
    console.error(
      "The following error occured: "+ textStatus, errorThrown
    );
  });
  request.always(function() {
    $inputs.prop("disabled", false);
  });
  event.preventDefault();
});

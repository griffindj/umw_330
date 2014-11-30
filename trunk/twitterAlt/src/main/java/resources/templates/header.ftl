<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>TwitterAlt</title>

    <!-- Bootstrap core CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/twitterAlt.css" rel="stylesheet">
	
    <!-- Bootstrap core JavaScript
    ================================================== -->
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<script src="js/jquery.confirm.min.js"></script>
	<script>
		$.confirm.options = {
		    title: "Confirmation Required to Proceed",
		    cancelButton: "Cancel",
		    post: true
		}
	</script>
	
  </head>
  <body>
  
  <div class="container" style="padding-top:20px;">
  
  
<!-- NAVBAR
================================================== -->
    <div class="navbar-wrapper">
      <div class="container">

        <div class="navbar navbar-static-top" role="navigation">
          <div class="container">
            <div class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
              	<li><img src="/images/twitterAltLogo.png" height="64" width="64"/></li>
              	<li><h1 class="col-md-4">TwitterAlt</h1></li>
              </ul>
              <ul class="nav navbar-nav pull-right">
                <li><a href="/login"><span class="glyphicon glyphicon-off"></span>Register/Login</a></li>
              </ul>
            </div>
          </div>
        </div>

      </div>
    </div>

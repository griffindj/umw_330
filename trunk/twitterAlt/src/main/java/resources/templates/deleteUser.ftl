<#include "header.ftl">

<div class="container">

	<h2>Are you sure you want to delete your account?</h2>

	<div class="container">
		<div class="col-md-6">
		
			<form id="delete-form" role="form" action="/deleteUser" method="post">
                          <input type = "text" value = "${user.username}"></input>
			  <button id="deleteBtn" type="submit" class="btn btn-primary">Yes! (Delete Account)</button>
			  <button id="cancelBtn" type="button" class="btn">No!</button>
			</form>
			
		</div>
	</div>

</div>

<script type="text/javascript">
//this gets all inputs on our form, and resets the value to "reset" our form when cancel is clicked
$( "#cancelBtn" ).click(function() {
    window.location = "/profile";
});
</script>

<#include "footer.ftl">

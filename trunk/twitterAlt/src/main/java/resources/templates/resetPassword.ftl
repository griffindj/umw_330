<#include "header.ftl">

<div class="container">

	<h2>Reset Password</h2>

	<div class="container">
		<div class="col-md-6">
		
			<form id="reset-form" role="form" action="/resetPassword" method="post">
			  <div class="form-group">
			    <label for="oldPassword">Old Password</label>
			    <input type="password" class="form-control" name="oldPassword" id="oldPassword" placeholder="Old Password">
			  </div>
              <div class="form-group">
			    <label for="confirmPassword">Old Password (Confirm)</label>
			    <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="Old Password (Confirm)">
			  </div>
			  <div class="form-group">
			    <label for="newPassword">New Password</label>
			    <input type="password" class="form-control" name="newPassword" id="newPassword" placeholder="New Password">
			  </div>
			  <button id="resetBtn" type="submit" class="btn btn-primary">Reset Password</button>
			  <button id="cancelBtn" type="button" class="btn">Cancel</button>
			</form>
			
		</div>
	</div>

</div>

<script type="text/javascript">
//this gets all inputs on our form, and resets the value to "reset" our form when cancel is clicked
$( "#cancelBtn" ).click(function() {
    $(':input','#reset-form')
    .not(':button, :submit, :reset, :hidden')
    .val('')
    .removeAttr('checked')
    .removeAttr('selected');
});
</script>

<#include "footer.ftl">

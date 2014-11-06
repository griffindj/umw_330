<#include "header.ftl">

<div class="container">

	<h2>Post Message</h2>

	<div class="container">
		<div class="col-md-6">
		
			<form role="form" action="/postMessage" method="post">
			  <div class="form-group">
			    <label for="message">Message</label>
			    <textarea class="form-control" rows="3" id="message" placeholder="Write a message"></textarea>
			  </div>
			  <button type="submit" class="btn btn-primary">Post Message</button>
			  <button type="button" class="btn" >Cancel</button>
			</form>
			
		</div>
	</div>

</div>


<#include "footer.ftl">

<#include "header.ftl">

<div class="container">

	<h2>Search by Tag</h2>

	<div class="container">
		<div class="col-md-6">
		
			<form role="form" action="/searchTag" method="post">
			  <div class="form-group">
			    <label for="search">Search</label>
			    <textarea class="form-control" rows="1" id="tag" placeholder="Enter tag to search"></textarea>
			  </div>
			  <button type="submit" class="btn btn-primary">Search</button>
			  <button type="button" class="btn" >Cancel</button>
			</form>
			
		</div>
	</div>

</div>


<#include "footer.ftl">

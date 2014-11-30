<#include "header.ftl">

<div class="container">

	<div class="container">
		<div class="col-md-5">
			<div class="panel panel-danger">
				<div class="panel panel-heading">
					<h3>Login / Register</h3>
				</div>
				<div class="panel panel-body">
					<form id="login-form" role="form" action="/login" method="post">
					  <div class="form-group">
					    <label for="email">Email</label>
					    <input type="email" class="form-control" name="email" id="email" placeholder="Enter email">
					  </div>
					  <div class="form-group">
					    <label for="password">Password</label>
					    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
					  </div>
					  <button id="loginBtn" type="submit" class="btn btn-primary">Login</button>
					  <button id="registerBtn" type="submit" class="btn btn-danger">Register</button>
					  <button id="cancelBtn" type="button" class="btn">Cancel</button>
					</form>
					<#if error??>
						<p class="label label-danger">${error}</p>
					</#if>
				</div>
			</div>
			
			<div class="panel panel-success">
				<div class="panel panel-heading">
					Word Cloud of public HashTags
				</div>
				<div class="panel panel-body">
					<div id="wordcloud" style="width: 300px; height: 300px; position: relative;"></div>
				</div>
			</div>
			
		</div>
		
		<!--- List of all Public messages (disregarding subscriptions) --->
		<div class="col-md-7">
			<div class="panel panel-info">
			  <div class="panel-heading">
			    <h3 class="panel-title">Public TwitterAlt Feed</h3>
			  </div>
				<div class="panel-body">
					<ul class="list-group">
				  		<#list publicMessageFeed as message>
						    <li class="list-group-item">
						    	<div>
						    		${message.text}
						    		<div class="label label-default pull-right">
						    			${message.author} wrote at ${message.date?datetime}
						    		</div>
						    	</div>
						    	<div>
						    		<#list message.mentions as mention> 
						    			<span class="label label-primary">${mention}</span>
						    		</#list>
						    		<#list message.hashtags as hashtag> 
						    			<span class="label label-danger wordCloudWords">${hashtag}</span>
						    		</#list>
						    	</div> 
						    </li>
						</#list>
					</ul>
				</div>
			</div>
		</div>
		
	</div>

</div>

<script type="text/javascript">
//this is the javascript code that allows us to add different behaviors to our register and cancel buttons
//this changes the action (where the form gets submitted to) to /register
$( "#registerBtn" ).click(function() {
	$("#login-form").attr("action", "/register");
	$("#login-form").submit();
});
//this gets all inputs on our form, and resets the value to "reset" our form when cancel is clicked
$( "#cancelBtn" ).click(function() {
    $(':input','#login-form')
    .not(':button, :submit, :reset, :hidden')
    .val('')
    .removeAttr('checked')
    .removeAttr('selected');
});

//this is the javascript for building our wordcloud
//first using jquery we get a list of all of our hashtag span tags
var hashtagSpans = $(".wordCloudWords");
//now we need to count how many tags there are of each word to calculate our weight
var wordcount = {};
for(i = 0; i < hashtagSpans.length; i++){
	//so for every span tag, check if it's already been counted and then increment
	if(!wordcount[$(hashtagSpans[i]).text()]){
		wordcount[$(hashtagSpans[i]).text()] =  1;
	}else{
		wordcount[$(hashtagSpans[i]).text()] = wordcount[$(hashtagSpans[i]).text()] + 1;
	}
}
//create our empty array of wordCloudWords which are JSON objects that have text, weight, and link properties
var wordCloudWords = [];
//for each word in our wordcount we construct a JSON object and push it onto our array
for(var key in wordcount){
	var word = {
		text: key,
		weight: wordcount[key]
	}
	wordCloudWords.push(word);
}
//now when the document is ready, simply call the jQCloud class passing it our array of words
$(document).ready(function() {
   $("#wordcloud").jQCloud(wordCloudWords);
});
</script>

<#include "footer.ftl">

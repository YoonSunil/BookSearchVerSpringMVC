function getByteLength(o,m,s,b,i,c){
	s = o.value;
    for(b=i=0;c=s.charCodeAt(i++);b+=c>>11?3:c>>7?2:1);
    if(m < b){
    	if(event.keyCode != '8'){
    		o.value = s.substr(0, s.length -1);
	    	alert(m+"バイト以上は入力できません。");
    	}
    }
}

function validateForm(form) {
	var input = form.getElementsByTagName("input");
	var error = document.getElementsByClassName("error")[0];
	for (var i = 0; i < input.length; i++) {
		if(input[i].value == ""){
			input[i].focus();
			error.innerHTML = "入力エラー ："+input[i].labels[0].innerText+"は必ず入力してください。";
			error.style.visibility = "visible";
			window.setTimeout(function(){ error.style.visibility = "hidden"}, 2000);
			return false;
		}
	}
	return true;
}

function clearForm(form){
	var input = form.getElementsByTagName("input");
	for (let dom of input) {
		if(dom.type != "radio"){
			dom.value = "";
		} else {
			dom.parentNode.children[0].checked = true;
		}
	}
	return false;
}

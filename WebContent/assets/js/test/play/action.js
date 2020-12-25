window.addEventListener("load", function() {
   var pageSection = document.querySelectorAll(".page");
   
   //section1
   var page1 = document.querySelector("#aPart1");
   var pageNextButton = page1.querySelector(".page-next-button");
   
   //section2
   var page2 = document.querySelector("#aPart2");
   var qna = document.querySelector(".qna");
   var order =  page2.querySelector(".order");
   var progress = page2.querySelector(".progress");
   var title = page2.querySelector(".title");
   var answerButtons = page2.querySelectorAll(".answer-button");

   var qId = getParam("qId");
   if( qId == undefined || qId == "")
      qId = 1;

   pageNextButton.onclick = function (e) {
      e.preventDefault();
      if (e.target.tagName != "A")
         return;
      e.target.parentElement.classList.add("d-done");
      e.target.parentElement.nextElementSibling.classList.remove("d-done");
      loadContent(getParam("id"),qId, null);
   }
   
   qna.onclick = function (e){
      if( e.target.tagName != "BUTTON")
         return;
         
      var answerValueArray = [];
      var answerValues = page2.querySelectorAll(".result1");
      if( e.target.classList.contains("result2-button") )
         answerValues = page2.querySelectorAll(".result2");
      /*else if( e.target.classList.contains("result3-button") )
         answerValues = page2.querySelectorAll(".result3");
      else if( e.target.classList.contains("result4-button") )
         answerValues = page2.querySelectorAll(".result4");
      */   
      for(var i=0; i<answerValues.length; i++)
         answerValueArray[i] = answerValues[i].value;
      qId++;      

      loadContent(getParam("id"), qId, answerValueArray);   
   }
   
   function getParam(pName) {
      var params = location.search.substr(location.search.indexOf("?")+1);
      var val = "";
      params = params.split("&");
      for(var i=0; i<params.length; i++){
         temp = params[i].split("=");
         if([temp[0]]==pName)
            val = temp[1];
      }
      return val;
   }
   
   function loadContent(id, qId, answerValueArray) {
      
      var request = new XMLHttpRequest();
      title.innerHTHML = "";
      answerButtons.innerHTML = "";
      order.innerHTML = "";
      progress.innerHTML = "";
         
      request.onload = function() {
         
         var contents = JSON.parse(request.responseText);
         var formSize = 2;
         var contentSize = contents.length;
         console.log(qId);
         if(qId > 6 ){
            location.href="/test/play/result?id="+id;               
            return; 
         }
            
         title.innerHTML = "";
         order.innerHTML = "";
         progress.innerHTML = "";   
         
         for(var i=0; i<contents.length; i++){
            var c = contents[i];
         }
         for(var i=0; i<answerButtons.length; i++){
            var button = answerButtons[i];
            
            button.innerHTML = "";
            button.value = contents[i].sScore;
            button.insertAdjacentHTML('beforeend', contents[i].aContent);                              
         }         
         title.insertAdjacentHTML('beforeend', c.qContent);            
         order.insertAdjacentHTML('beforeend', qId);
         progress.insertAdjacentHTML('beforeend', qId+'/'+6);
      };
      
      request.open("GET", "/test/play/content?id="+id+"&qId="+qId+"&answerVal="+answerValueArray+"&type=c", true);
      
      request.send();
   };
   
});
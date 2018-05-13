$(document).ready(function() {
     prepareTaskList();
});

function prepareTaskList(){
   var userId = window.location.hash.substring(1)
   $.ajax({
          type: 'get',
          url: "/todolist/rest/users/"+userId+"/tasks",
          success: function (result) {
             var i = 0;
             var tasks = result.taskList;
             for (i=0; i < tasks.length; i++ ){
                  var task = tasks[i]
                  var li = document.createElement("li");
                  li.id = task.id
                  li.setAttribute("lastUpdate", task.lastUpdate);
                  var t = document.createTextNode(task.description);

                  li.appendChild(t);

                  // Add a "checked" symbol when clicking on a task
                  li.addEventListener('click', function(ev) {
                     if (ev.target.tagName === 'LI') {
                         ev.target.classList.toggle('checked');
                     }
                  }, false);

                  document.getElementById("tasklist").appendChild(li);

                  // Create delete icon button
                  var txt = document.createTextNode("\u00D7");
                  var span = document.createElement("SPAN");
                  span.className = "delete";
                  span.appendChild(txt);
                  li.appendChild(span);
              }
          },
          error: function (e) {
             console.log(e);
          }
     });
}

// Create a new task
function addTask() {
  var li = document.createElement("li");
  var description = document.getElementById("description").value;
  var t = document.createTextNode(description);
  li.appendChild(t);
  if (inputValue === '') {
       alert("You must write something!");
  } else {
      document.getElementById("tasklist").appendChild(li);
  }
  document.getElementById("description").value = "";

  var span = document.createElement("SPAN");
  var txt = document.createTextNode("\u00D7");
  span.className = "close";
  span.appendChild(txt);
  li.appendChild(span);

  for (i = 0; i < close.length; i++) {
     close[i].onclick = function() {
       var div = this.parentElement;
       div.style.display = "none";
     }
  }
}

function removeTask() {
    var div = this.parentElement;
    var taskId
    div.style.display = "none";
    $.ajax({
        type: 'delete',
        url: "/todolist/rest/users/"+userId+"/tasks/"+taskId,
        success: function (result) {
           var i = 0;
           var tasks = result.taskList;
           for (i=0; i < tasks.length; i++ ){
                      var li = document.createElement("li");
                      var t = document.createTextNode(tasks[i].description);
                      li.appendChild(t);
                      document.getElementById("myUL").appendChild(li);

                      // Create delete icon button
                      var txt = document.createTextNode("\u00D7");
                      var span = document.createElement("SPAN");
                      span.className = "delete";
                      span.appendChild(txt);
                      span.onClick()
                      li.appendChild(span);
                  }
              },
              error: function (e) {
                 console.log(e);
              }
         });
}
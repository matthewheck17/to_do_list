function formResults(form){
   var tasks = {
        title: form.title.value, 
        content: form.description.value
   }

    var work = JSON.stringify(tasks);
   alert(work);
}
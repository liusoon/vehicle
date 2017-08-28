function check (form)
{
   var reg = /^\s*|\s*$/g;
   if (form.password.value.replace(reg,'') == "")
   {
      alert ("请输入密码");
      form.password.focus ();
      return false;
   }else if(form.password.value.replace(reg,'').length<4){
       alert ("密码长度至少是四位");
       form.password.focus ();
       return false;
    }else 
    {
       return true;
    }
}

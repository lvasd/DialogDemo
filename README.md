# DialogDemo
emmm  常用的AlertDialog  DialogFragment  做个总结 可能会有坑！！

### 需要注意
        DialogFragment 中 onCreateView()与onCreateDialog()不能同时使用,
        onCreateView() 请看 BottomDialogFragment,
        onCreateDialog() 请看 ConfirmDialogFragment,

### 个人感觉：
        如果只是简单的确认取消,使用AlertDialog就可以了
        DialogFragment 好处是可以当做Fragment来处理 可以使用Fragment的一些方法
### 目前个人疑问
        使用dialogFragment onCreateView() 如果不在onStart()方法中设置
         window.setBackgroundDrawableResource(android.R.color.transparent);
         WindowManager.LayoutParams params = window.getAttributes();
         params.width = WindowManager.LayoutParams.MATCH_PARENT;
         window.setAttributes(params);

       onCreateView（）中的布局使用的是WRAP_CONTENT不管在xml中如何设置
       不知道有没有什么好的解决办法
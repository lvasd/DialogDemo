# DialogDemo
emmm  常用的AlertDialog  DialogFragment  BottomSheet BottomSheetDialog BottomSheetDialogFragment做个总结
有坑！！需谨慎！！

### 需要注意
        DialogFragment 中 onCreateView()与onCreateDialog()不能同时使用,
        onCreateView() 请看 BottomDialogFragment,
        onCreateDialog() 请看 ConfirmDialogFragment,

### 个人感觉：
        如果只是简单的确认取消,使用AlertDialog就可以了
        DialogFragment 好处是可以当做Fragment来处理 可以使用Fragment的一些方法
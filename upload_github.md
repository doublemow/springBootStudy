# git 基本命令
- 进入Git可管理的仓库
- 在命令行中输入git init把这个文件夹变成Git可管理的仓库（设置成功后会在文件夹中产生一个.git 文件夹）将需要上传到github的文件全部复制到此目录下
- 然后通过git add .(注意这个"."，是有空格的，"."代表这个test这个文件夹下的目录全部都提交。你也可以通过git add 文件名  提交指定的文件)把文件添加到缓存区（可以通过git status命令，查看下现在的状态）
- 然后使用命令 git commit -m "注释"
- 然后配置git和github（https://blog.csdn.net/s740556472/article/details/80318886）
- git remote add origin https://github.com/unlimitbladeworks/Data-Struts-Learning.git

## 上传命令
- git push -u origin master(如果github仓库是空的使用此命令)
- 如果仓库不是空则需要先合并（git pull --rebase origin master）
- 再输入 git push origin master
- 等远程仓库里面有了内容之后，下次再从本地库上传内容的时候只需要（git push origin master）
- https://www.cnblogs.com/crazyStar/articles/7354894.html

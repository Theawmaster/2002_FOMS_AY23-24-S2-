export PS1="\[\033[36m\]\u@\h \[\033[33m\]\w\[\033[32m\]\$(git branch 2>/dev/null | grep '^*' | colrm 1 2)\[\033[00m\] $ "


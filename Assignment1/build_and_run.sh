#!/usr/bin/env sh

javacc ccalparse.jj
javac ccalparse.java

java ccalparse tests/main1.ccl
echo MAIN1 FIN
java ccalparse tests/main2.ccl
echo MAIN2 FIN
java ccalparse tests/main3.ccl
echo MAIN3 FIN
java ccalparse tests/comments.ccl
echo COMMENTS FIN
java ccalparse tests/simple_func.ccl
echo SIMPLE_FUNC FIN
java ccalparse tests/scopes.ccl
echo SCOPES FIN
java ccalparse tests/functions.ccl
echo FUNCTIONS FIN

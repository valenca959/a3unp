@echo off
set source=./dist/lib
set destination=lib

if not exist %source% (
    echo Diretório de origem não encontrado!
    exit /b
)

if not exist %destination% (
    echo Diretório de destino não encontrado!
    echo criando diretório de destino...
    mkdir %destination%
    echo Diretório de destino criado com sucesso!
)

for /R %source% %%F in (*.old) do (
    copy "%%F" %destination%
)

ren %destination%\AbsoluteLayout.jar.old AbsoluteLayoutTeste.jar 
ren %destination%\flatlaf-3.4.1.jar.old flatlaf-3.4.1.jar
ren %destination%\mysqlconnectorjava8.0.30_20240513141304.jar.old mysqlconnectorjava8.0.30.jar


echo Libraries movidas com sucesso!
# The Art of Programming
Repositorio para las clases de la asignatura optativa "The Art of Programming" (AoP) de 3º de Ingeniería Informática de la UPM ([Guía docente 2020/2021](https://www.upm.es/comun_gauss/publico/guias/2020-21/1S/GA_10II_105000439_1S_2020-21.pdf))

## Importante
* Este respositorio tiene como objetivo el de servir de soporte para la colaboración entre los alumnos de la asignatura que quieran aportar cualquier sugerencia, ya sea con su código o con mejoras si las hubiera.
* El uso correcto del respositorio es responsabilidad de cada uno. Trata de resolver los problemas que se planteen por ti mismo, y si no encuentras manera de solucionarlo recurre a tus docentes o compañeros antes de encontrar una posible solución.
* El código aquí presente, aunque pase las pruebas, no significa que esté correcto.

## Puesta a punto
Para llevar a cabo la asignatura se recomienda utilizar cualquier lenguaje de programación soportado por el UVa Judge. No obstante, utilizaremos Java puesto que se le dará soporte por parte de los profesores. Una vez tengamos configurado y preparado nuestro entorno de desarrollo, vamos a intentar trabajar con Git (y Github en este caso).

Para ello, debemos [descargarnos la última versión de Git](https://git-scm.com/downloads) para nuestro sistema operativo (si tienes un SO basado en UNIX, como cualquier distribución GNU/Linux, seguramente git vendrá instalado por defecto).

### Tutorial básico de Git

Una vez tengamos correctamente instalado git, podemos comprobarlo introduciendo lo siguiente en nuestro terminal:

`git config --list`

A continuación, configuraremos nuestras variables locales de usuario y email:

`$ git config --global user.name tu_usuario`

`$ git config --global user.email tu@email.com`

El siguiente paso será clonar todo el contenido de este repositorio Git (Github) en nuestra carpeta local. Para ello, nos situamos en el directorio desde el cual queramos crear la carpeta que contendrá todo el repositorio (por ejemplo en `Desktop`) y a continuación insertamos el siguiente comando:

`$ git clone https://github.com/polkiko/aop.git nombre_carpeta`

Y entramos en nuestra carpeta:

`$ cd nombre_carpeta`

De esta manera tendremos directamente en nombre_carpeta todos los archivos del repositorio en nuestra máquina, pudiendo trabajar sobre ellos si queremos.

### Nota
Si conoces más a fondo Git puedes aportar cualquier mejora o ampliación de cualquiera de los ficheros. Para ello, puedes [ponerte en contacto conmigo](https://t.me/polkiko), y crearemos una rama con tu nombre para trabajar sobre ella (dejar la rama `master` libre siempre).

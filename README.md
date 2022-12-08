# Taller 2 de Hibernate + Taller 3 Hibarnate (JPA)

El taller consiste en un posible página web que funcionaría como "hub" de videos embebidos / empotrados, en dónde cualquiera puede subir un enlace a un video, ya sea de YouTube, BitChute, Odyssey, Nico Nico Video, etc., y ya sea siendo el autor o no de este.
Los usuarios que comparten videos embebidos son seguidos por otros usuarios que tal vez les interese cierto tema o categoría de videos, por ejemplo.
Se permite además realizar votos (voto positivo o negativo) a cuentas, posts (enlaces a videos), y comentarios.
Se permite escribir comentarios a tanto posts, como cuentas de usuario o incluso responder a un comentario.

El modelo relacional sería el siguiente:
![Modelo Relacional Hibernate Taller 2 JPA](https://user-images.githubusercontent.com/104576450/204555763-dfc3e070-78c0-4f00-9ba9-073aa668d1cc.png)

En el "main" de la aplicación se crear la sesión y se llaman a las funciones de la clase "InsertionDemo". Esta clase es dónde realizariamos las inserciones mediante los ManagementService y posteriormente mostraríamos el contenido de las tablas para ver que las inserciones se han hecho correctamente.

He de reconocer que se extiende / aleja demasiado del taller original, pero es debido a que me he centrado mucho en llevarlo a algo que me interesaba implementar.
La idea, por cierto, es libre para cualquiera :) aunque personalmente dudo mucho que pueda llegar a algún lado, ni tampoco sé si ya existe en la realidad.

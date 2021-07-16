## Università degli studi del Sannio
## Esame di Ingegneria del Software - a.a. 2020/2021

# Proposta di Formazione di un Gruppo


![C++ Var Types](https://github.com/AccaEmme/CARE/blob/main/docs/logo.jpg)

## &quot;**C.A.R.E. - Centro Accoglienza Regionale Ematica&quot;**
Progetto per la fornitura di un sistema software per la gestione integrata delle scorte di sangue per la rete dei centri trasfusionali territoriali di &quot;UnaRegione&quot;

Versione _1_ del _17.03.2021_


Checkout the documentation on the [Wiki page](https://github.com/AccaEmme/CARE/wiki/1.0-Problem-Statement) \[lang: IT\]:

# ==Slides==
 - [PDF presentazione](https://github.com/AccaEmme/CARE/blob/main/docs/CARE.pdf)
 - [Google Doc](https://docs.google.com/presentation/d/12kt6T9zaPwv3toitpu3gyocXufYmGekspI7-59VSSkM/edit?usp=sharing)(presentazione con note relatori e animazioni)


# ==License== <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/Cc-by_new_white.svg/24px-Cc-by_new_white.svg.png"> <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Cc-nc_white.svg/24px-Cc-nc_white.svg.png"> <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Cc-sa.svg/24px-Cc-sa.svg.png">
Licenze Creative Commons - CC BY-NC-SA.<br>
It allows you to distribute, modify, create derivative works from the original, but not for commercial purposes, provided that you: acknowledge adequate authorship, provide a link to the license and indicate if any changes have been made; and that the new work is given the same license as the original (therefore any derivative work will not be permitted for commercial use).


# ==Installation==
Prerequisites:
 - MySQL >= 8.0 on localhost:3306
 - ../../URI.xml with your personal MongoDB credentials: 
 ```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
	<entry key="username"></entry>
	<entry key="password"></entry>
	<entry key="db_host">.ksjti.mongodb.net/test</entry>
	<entry key="db_name">CARE</entry>
	<entry key="requests">requests</entry>
	<entry key="bloodbags">bloodbags</entry>
</properties>
```

# ==Screenshot==
<img src="https://github.com/AccaEmme/CARE/raw/main/docs/Capitolo8/MainCode.png">
<img src="https://raw.githubusercontent.com/AccaEmme/CARE/main/docs/Capitolo8/requested-accepted-arrived-imported.png">

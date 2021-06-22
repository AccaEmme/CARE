use locations;


-- mostra tutti i comuni di una determinata regione

select r.nome as "nome regione", p.nome as "nome provincia",c.nome as "nome comune" 
	from 	comuni c join province p join regioni r
    on 		c.id_provincia = p.id and p.id_regione = r.id
    where 	c.nome = "apice";

-- controllare se una determinata tupa esiste
-- se il count restituisce 1 vuol dire che la tupla esiste
select count(*) 
	from 	regioni r join province p join comuni c
    on 		c.id_provincia = p.id and p.id_regione = r.id
		where 	r.nome = "Campania" 	and
				p.nome = "Benevento" 	and
                c.nome = "Apice";

-- controlla se una determinata tupla esiste
-- ritorna le componenti per creare un oggetto (da creare in java) location

select r.nome as "region", p.nome as "province", c.nome as "city"
	from regioni r join province p join comuni c
    on 		c.id_provincia = p.id and p.id_regione = r.id
		where 	r.nome = "Campania" 	and
				p.nome = "Benevento" 	and
                c.nome = "Apice";
                
-- ritorna la posizione di una città specifica
select c.nome as "nome comune", c.latitudine as "latitudine", c.longitudine as "longitudine"
	from regioni r join province p join comuni c
		on 		c.id_provincia = p.id and p.id_regione = r.id
			where 	r.nome = "Campania" 	and
					p.nome = "Benevento" 	and
					c.nome = "Apice";
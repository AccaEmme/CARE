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
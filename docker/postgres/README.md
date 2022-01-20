!! Attention !!

Le docker est généré à partir du fichier de config docker-compose.yml.
Les données stockées dans la db sont en réalité stockés dans le dossier postgres-data. 

Donc méfiance lors de la manipulation 
-> Créez des branchs si vous voulez push des données dans la base

Pour lancer le conteneur docker:

```bash
    sudo docker-compose up -d
```

Pour se connecter via le terminal à la console du container:

```bash
    sudo docker exec -it  postgres_db_1 bash
```

Pour fermer le container une fois terminé:

```bash
    sudo docker-compose down
```
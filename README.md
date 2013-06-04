# joplet

Joplet is a demo web-app that uses
[jopbox](http://github.com/samrat/jopbox) to connect to Dropbox.

You can see a live demo too: http://jopbox-demo.herokuapp.com/

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

### Setup
You can run this either locally or on Heroku(a free instance works
fine). Make sure you have created a
[Dropbox app](https://www.dropbox.com/developers/apps) first.

#### Local
If you use Foreman, you can store your API keys in an `.env` file:

    DROPBOX_KEY=YOUR-KEY
    DROPBOX_SECRET=YOUR-SECRET
    CALLBACK_URL=http://localhost:3000/auth
    
You might have to change the port number on the `CALLBACK_URL`. Run
the app using `foreman start`.

If you don't use Foreman, set up the environment variables in your
bash prompt like this:

    export DROPBOX_KEY=YOUR-KEY
    
Do that for your API secret and callback URL as well.

Now, run:

    lein ring server
    
or

    lein trampoline run -m joplet.web
    
#### Heroku
If you want to run this on heroku, first do a `heroku create`.

Then,

    heroku config:set DROPBOX_KEY=YOUR_KEY
    heroku config:set DROPBOX_SECRET=YOUR_SECRET

After that, push to Heroku:

    git push heroku master
    
## License

Copyright © 2013 Samrat Man Singh

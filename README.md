# Quest

Quest is a RP GTD app for users who want to have fun making progress.

## Roadmap

- Quests can be rapidly picked up
- Quests are easily abandoned
- Quests can be shared
- Quests are considered accepted when done conditions and sub tasks are present
- Quests are grouped by zone
- Quests can be chained
- Quests are turned in for rewards
- Quests have a daily cap
- Quests have a max backlog
- Quests are archived on completion
- Quests can yield achievements

## Tech Stack

**Client:** ClojureScript, Re-frame, Bulma

**Server:** Clojure

## Run Locally

Clone the project

```bash
  git clone https://github.com/ChristianAlexa/quest.git
```

Go to the project directory

```bash
  cd quest
```

First time, grant permissions to run build script

```bash
  chmod +x ./dev/build_resources.sh
```

Periodically, run the build script

```bash
  ./dev/build_resources.sh
```

Start the server in VSCode using Calva

1. With the project open in VSCode, run `ctrl + alt + 0`
2. select `shadow-cljs`
3. select `:app`
4. select `node-repl`

Open build manager to manage app status

1. Navigate to <http://localhost:9630/dashboard>
2. click builds
3. select app
4. click force compile

Navigate to <http://localhost:3000>

## Documentation

This README was built with <https://readme.so/>

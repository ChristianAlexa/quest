# Quest

Quest is a RP GTD app for users who want to have fun making progress.

## Roadmap

- [ ] Quests can be rapidly picked up
- [ ] Quests are easily abandoned
- [ ] Quests can be shared
- [ ] Quests are considered accepted when done conditions and sub tasks are present
- [ ] Quests are grouped by zone
- [ ] Quests can be chained
- [ ] Quests are turned in for rewards
- [ ] Quests have a daily cap
- [ ] Quests have a max backlog
- [ ] Quests are archived on completion
- [ ] Quests can yield achievements

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

Start the server

```bash
  make start
```

View the app at <http://localhost:3000>
Manage the app at <http://localhost:9630/dashboard>

Jack into nREPL for VSCode Calva inline form evaluation

1. Click on nREPL (bottom left tray)
2. Select connect to a running REPL server in your project
3. Select shadow-cljs
4. Enter localhost:3333
5. Select node-repl

You can now evaluate top level forms with `alt+enter` or current forms with `ctrl+enter`.

## Documentation

This README was built with <https://readme.so/>

# panglossia

An urban dictionary style interactive glossary with some useful abstractions about contexts. Mostly meant for practicing re-frame/reagent.

## TODO

 * ~~Figure out how the CSS is not getting applied from styles.....~~
  * Wasn't using the right style names...... :(
 * Make a slugify function for words
 * Figure out how to do DB validation to prevent fucked up state
 * Refactor styles mostly out of view.cljs, possibly move whole components out
 * Make a word page
 * Make the edit page/update functionality
 * Build the server side

## Things I learned

 * less compilation needs to be an active process
 * Why have the `{:key (:id foo)}` when making a vector of components?
  * Because if you don't then atoms local to components will kind of inherit by their index
  * filtering a list will cause the 1st component of the filtered list to inherit the first component of the unfiltered list's atom states leading to some wonky behavior.


## Development Mode

### Compile css:

Compile css file once.

```
lein less once
```

Automatically recompile css file on change.

```
lein less auto
```

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

### Run tests:

```
lein clean
lein doo phantom test once
```

The above command assumes that you have [phantomjs](https://www.npmjs.com/package/phantomjs) installed. However, please note that [doo](https://github.com/bensu/doo) can be configured to run cljs.test in many other JS environments (chrome, ie, safari, opera, slimer, node, rhino, or nashorn).

## Production Build

```
lein clean
lein cljsbuild once min
```

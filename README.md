# update-readme

[![Build Status](https://travis-ci.org/HughPowell/update-readme.svg?branch=master)](https://travis-ci.org/HughPowell/update-readme)
[![Clojars Project](https://img.shields.io/clojars/v/update-readme.svg)](https://clojars.org/update-readme)

A Leiningen plugin to update versions of dependencies in the README.md file.

## Usage

Put `[update-readme "0.1.1"]` into the `:plugins` vector of your project.clj.

Basic usage as follows

    $ lein update-readme

Probably a more useful way to use this is as part of release tasks

    :release-tasks [["vcs" "assert-committed"]
                    ["change" "version" "leiningen.release/bump-version" "release"]
                    ["update-readme"]
                    ["vcs" "commit"]
                    ["vcs" "tag" "--no-sign"]
                    ["deploy"]
                    ["change" "version" "leiningen.release/bump-version"]
                    ["update-readme"]
                    ["vcs" "commit"]
                    ["vcs" "push"]])

## Ownership and License

Copyright © 2017 Hugh Powell

The contributors are listed in AUTHORS. This project uses the [MPL v2 license](https://www.mozilla.org/en-US/MPL/2.0/), see LICENSE.

update-readme uses the [C4 (Collective Code Construction Contract)](https://rfc.zeromq.org/spec:42/C4) process for contributions.

update-readme uses the [clojure-style-guide](https://github.com/bbatsov/clojure-style-guide) for code style.

To report an issue, use the update-readme issue tracker at [github.com](https://github.com/HughPowell/update-readme/issues).
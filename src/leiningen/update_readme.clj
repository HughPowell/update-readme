(ns leiningen.update-readme
  "Copyright (c) 2017.
  This Source Code Form is subject to the terms of the Mozilla Public
  License, v. 2.0. If a copy of the MPL was not distributed with this
  file, You can obtain one at http://mozilla.org/MPL/2.0/."
  (require [clojure.java.shell :as shell]
           [clojure.string :as string]
           [clojure.java.io :as io]))

(defn- construct-regex [project-name version]
  (letfn [(construct-line [regex]
            (str "\\[" (string/replace project-name #"\-" "\\\\-") " \"" regex "\"\\]"))]
    (-> version
        (string/replace #"-" "\\\\-")
        (string/replace #"\d+" "[0-9]+")
        (string/replace #"\." "\\\\.")
        construct-line
        re-pattern)))

(defn- update-line [project-name version]
  (let [regex (construct-regex project-name version)
        new-line (str "[" project-name " \"" version "\"]")]
    (fn [line] (string/replace line regex new-line))))

(defn ^:no-project-needed update-readme
  "Update the dependency version in the README.md file"
  [{:keys [name version] :as project} & args]
  (->> (with-open [reader (io/reader "README.md")]
         (doall (line-seq reader)))
       (map (update-line name version))
       (interpose "\n")
       (apply str)
       (spit "README.md")))

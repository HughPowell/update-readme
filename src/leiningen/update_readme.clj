(ns leiningen.update-readme
  (require [clojure.java.shell :as shell]
           [clojure.string :as string]
           [clojure.java.io :as io]))

(defn- line-pattern [version project-name]
  (str "         [" project-name " \"" version "\"]"))

(defn- construct-version-line [project-name version]
  (-> version
      (string/replace #"\d+" "[0-9]+")
      (string/replace #"\." "\\\\.")
      (line-pattern project-name)))

(defn- update-line [project-name version]
  (fn [line]
    (string/replace
      line
      (re-pattern (construct-version-line project-name version))
      (line-pattern version project-name))))

(defn ^:no-project-needed update-readme
  "Update the dependency versions in the README.md file"
  [{:keys [name version] :as project} & args]
  (->> (with-open [reader (io/reader "README.md")]
         (doall (line-seq reader)))
       (map (update-line name version))
       #(with-open [writer (io/writer "README.md")]
          (doseq [line %]
            (.write writer line)))))

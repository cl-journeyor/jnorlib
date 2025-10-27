(ns jnorlib.core
  "Supplementary API.")

(defn between?
  "Checks whether `lower <= c <= upper` is true."
  [c lower upper]
  (and
   (>= (compare c lower) 0)
   (<= (compare c upper) 0)))

(defn lines
  "Returns a sequence with the lines from a file read with the provided
  `java.io.BufferedReader` as `in`.
  See also [[clojure.java.io/reader]]."
  [in]
  (->> (repeat #(.readLine in))
       (map #(%))
       (take-while #(not (nil? %)))))

(defn matches?
  "Checks whether the string `s` matches against the RegEx Pattern `re`."
  [^String s re]
  (-> (re-matcher re s)
      re-find
      nil?
      not))

(defn throws?
  "Invokes the provided `f` to check whether it throws an exception."
  [f]
  (try
    (not (any? (f)))
    (catch Exception _ true)))

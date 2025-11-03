(ns jnorlib.core
  "Supplementary API.")

(defn between?
  "Checks whether `lower <= c <= upper` is true."
  [c lower upper]
  (and
   (>= (compare c lower) 0)
   (<= (compare c upper) 0)))

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

(defn typed
  "Takes pairs in the form of [Class instance] and throws if an instance does
  not match its associated type. Returns nil."
  [& pairs]
  (doseq [[clazz val] pairs]
    (when-not (instance? clazz val)
      (throw (ClassCastException. (str
                                   (type val)
                                   " cannot be cast to "
                                   clazz))))))

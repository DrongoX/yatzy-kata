This solution preserves all the public APIs of Yatzy service: fields and method signatures.

That should represent 100% safe refactoring in case if Yatzy service is part of the larger system where we can't trace all the usages (Reflection, frameworks, other projects etc)
/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.hibernate.validator.internal.util.scriptengine;

import static org.hibernate.validator.internal.util.logging.Messages.MESSAGES;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.hibernate.validator.cfg.scriptengine.ScriptEvaluator;
import org.hibernate.validator.cfg.scriptengine.ScriptEvaluatorFactory;
import org.hibernate.validator.cfg.scriptengine.impl.AbstractCacheableScriptEvaluatorFactory;

/**
 * Factory responsible for the creation of {@link ScriptEvaluatorImpl}s. This
 * class is thread-safe.
 *
 * @author Gunnar Morling
 * @author Kevin Pollet &lt;kevin.pollet@serli.com&gt; (C) 2011 SERLI
 * @author Marko Bekhta
 */
public class DefaultLookupScriptEvaluatorFactory extends AbstractCacheableScriptEvaluatorFactory {

	private DefaultLookupScriptEvaluatorFactory() {
	}

	/**
	 * Retrieves an instance of this factory.
	 *
	 * @return A script evaluator factory. Never null.
	 */
	public static ScriptEvaluatorFactory getInstance() {
		return new DefaultLookupScriptEvaluatorFactory();
	}

	@Override
	protected ScriptEvaluator createNewScriptEvaluator(String languageName) throws ScriptException {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName( languageName );

		if ( engine == null ) {
			throw new ScriptException( MESSAGES.unableToFindScriptEngine( languageName ) );
		}

		return new ScriptEvaluatorImpl( engine );
	}
}

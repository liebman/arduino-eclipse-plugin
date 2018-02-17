package io.sloeber.core.toolchain;

import org.eclipse.cdt.core.envvar.IEnvironmentVariable;
import org.eclipse.cdt.internal.core.envvar.EnvironmentVariableManager;
import org.eclipse.cdt.managedbuilder.envvar.IBuildEnvironmentVariable;

public class BuildEnvironmentVariable implements IBuildEnvironmentVariable {
	protected String fName;
	protected String fValue;
	final protected String fDelimiter= EnvironmentVariableManager.getDefault().getDefaultDelimiter();
	protected int fOperation;

	public BuildEnvironmentVariable(String name, String value, int op) {
		fName = name;
		fOperation = op;
		fValue =value;

	}


	public BuildEnvironmentVariable(String name){
		this(name,null,ENVVAR_REPLACE);
	}

	public BuildEnvironmentVariable(String name, String value){
		this(name,value,ENVVAR_REPLACE);
	}

	public BuildEnvironmentVariable(String name, String value, String delimiter){
		this(name,value,ENVVAR_REPLACE);
	}

	public BuildEnvironmentVariable(IEnvironmentVariable var){
		this(var.getName(),var.getValue(),var.getOperation());
	}

	@Override
	public String getName(){
		return fName;
	}

	@Override
	public String getValue(){
		return fValue;
	}

	@Override
	public int getOperation(){
		return fOperation;
	}

	@Override
	public String getDelimiter(){
		return fDelimiter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fDelimiter == null) ? 0 : fDelimiter.hashCode());
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + fOperation;
		result = prime * result + ((fValue == null) ? 0 : fValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof IEnvironmentVariable))
			return super.equals(obj);
		IEnvironmentVariable other = (IEnvironmentVariable)obj;
		if (!equals(fName, other.getName()))
			return false;
		if (!equals(fValue, other.getValue()))
			return false;
		if (!equals(fDelimiter, other.getDelimiter()))
			return false;
		if (fOperation != other.getOperation())
			return false;
		return true;
	}

	// Helper method to check equality of two objects
	private boolean equals(Object obj1, Object obj2) {
		if (obj1 == obj2)
			return true;
		else if (obj1 == null)
			return false;
		else
			return obj1.equals(obj2);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (fName != null)
			sb.append(fName);
		if (fValue != null)
			sb.append("=").append(fValue); //$NON-NLS-1$
		sb.append(" ").append(fDelimiter); //$NON-NLS-1$
		switch (fOperation) {
			case ENVVAR_REPLACE:
				sb.append(" [REPL]"); //$NON-NLS-1$
				break;
			case ENVVAR_REMOVE:
				sb.append(" [REM]"); //$NON-NLS-1$
				break;
			case ENVVAR_PREPEND:
				sb.append(" [PREP]"); //$NON-NLS-1$
				break;
			case ENVVAR_APPEND:
				sb.append(" [APP]"); //$NON-NLS-1$
				break;
			default:
				sb.append(" [NONE]"); //$NON-NLS-1$
				break;
		}
		return sb.toString();
	}



}

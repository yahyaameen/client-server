package messageclasses;

public abstract class WebMessage {
	
	
	protected ContentType contentType;
	
	
	
	public ContentType getContentType() {
		return contentType;
	}
	
	
	public void setContentType(ContentType contentType) {
		
		this.contentType = contentType;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebMessage other = (WebMessage) obj;
		if (contentType != other.contentType)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "WebMessage [contentType=" + contentType + "]";
	}
	
	
	
	
	
	

}

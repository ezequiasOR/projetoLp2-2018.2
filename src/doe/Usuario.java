package doe;

public abstract class Usuario {
    
    protected String nome;
    protected String email;
    protected String celular;
    protected String classe;
    protected String id;
    protected Validador validador;
    
    public Usuario(String id, String nome, String email, String celular, String classe) {
    	this.validador.validaCadastroDeDoador(id, nome, email, celular, classe);
    	
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.classe = classe;
        this.id = formataId(id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    protected String formataId(String documento) {
    	if (this.validador.validaTipoDeUsuario(documento)) {
    		id = "";
    		
    		for (int i = 0; i<documento.length();i++) {
    			id += documento.charAt(i);
    			if (i==2) {
    				id += '.';
    			}
    			else if (i == 5) {
    				id += '.';
    			}
    			else if (i == 8) {
    				id += '-';
    			}
    		}
    		return id;
    	}
    	else {
    		id = "";
    		
    		for (int i = 0; i<documento.length();i++) {
    			id += documento.charAt(i);
    			if (i==1) {
    				id += '.';
    			}
    			else if (i == 4) {
    				id += '.';
    			}
    			else if (i == 7) {
    				id += '/';
    			}
    			else if (i == 11) {
    				id += '-';
    			}
    		}
    		return id;
    	}
    }

    
}
	
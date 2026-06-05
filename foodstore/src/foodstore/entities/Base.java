package foodstore.entities;

import java.time.LocalDateTime;

public abstract class Base {
    
    private long id;
    private boolean eliminado;
    private LocalDateTime createdAt;
    
    public Base(long id) {
        this.setId(id);
        this.setEliminado(false);
        this.setCreatedAt(LocalDateTime.now());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Id provisto inválido");
        }
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        if (createdAt != null && this.createdAt == null) {            
            this.createdAt = createdAt;
        } else {
            System.out.println("Operación imposible, el objeto ya fue creado");
        }
    }

    @Override
    public abstract String toString();

    
}

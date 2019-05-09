package restapi.enumerations;

public enum TagEnum {

  neutro(0, "neutro"),
  veludo(1, "veludo"),
  couro(2, "couro"),
  basics(3, "basics"),
  festa(4, "festa"),
  workwear(5, "workwear"),
  inverno(6, "inverno"),
  boho(7, "boho"),
  estampas(8, "estampas"),
  balada(9, "balada"),
  colorido(10, "colorido"),
  casual(11, "casual"),
  liso(12, "liso"),
  moderno(13, "moderno"),
  passeio(14, "passeio"),
  metal(15, "metal"),
  viagem(16, "viagem"),
  delicado(17, "delicado"),
  descolado(18, "descolado"),
  elastano(19, "elastano");

  private int id;
  private String tag;

  TagEnum(int id, String tag) {
    this.id = id;
    this.tag = tag;
  }

  public static TagEnum getById(int idParam) {
    for(TagEnum e : values()) {
      if(e.id == idParam) return e;
    }
    return null;
  }

  public String getTag() {
    return tag;
  }

}

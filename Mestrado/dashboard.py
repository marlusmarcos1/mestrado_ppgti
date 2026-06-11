import pandas as pd
import streamlit as st
import requests
from streamlit_autorefresh import st_autorefresh

# ======================
# CONFIGURAÇÃO
# ======================

st.set_page_config(
    page_title="Sistema de Auditoria Inteligente",
    layout="wide"
)

st_autorefresh(
    interval=5000,
    key="dashboard_refresh"
)

# ======================
# API
# ======================

estado = requests.get(
    "http://localhost:8000/estado"
).json()

eventos = requests.get(
    "http://localhost:8000/eventos"
).json()

# ======================
# TÍTULO
# ======================

st.title("🔍 Sistema de Auditoria Inteligente")
st.caption(
    "Monitoramento inteligente de objetos utilizando Visão Computacional e IA"
)

# ======================
# MÉTRICAS
# ======================

total_objetos = sum(estado.values()) if estado else 0
total_eventos = len(eventos)
tipos_objetos = len(estado)

col1, col2, col3 = st.columns(3)

with col1:
    st.metric(
        "📦 Objetos Detectados",
        total_objetos
    )

with col2:
    st.metric(
        "🚨 Eventos Registrados",
        total_eventos
    )

with col3:
    st.metric(
        "🏷️ Tipos de Objetos",
        tipos_objetos
    )

st.divider()

# ======================
# INVENTÁRIO + EVENTOS
# ======================

col_esq, col_dir = st.columns([1, 1])

# ======================
# INVENTÁRIO
# ======================

with col_esq:

    st.subheader("📦 Inventário Atual")

    if not estado:
        st.info("Nenhum objeto detectado.")
    else:

        cols = st.columns(2)

        for i, (objeto, qtd) in enumerate(estado.items()):

            with cols[i % 2]:

                st.markdown(
                    f"""
                    <div style="
                        border:1px solid #dcdcdc;
                        border-radius:12px;
                        padding:15px;
                        margin-bottom:15px;
                        text-align:center;
                        box-shadow:0 2px 5px rgba(0,0,0,0.1);
                    ">
                        <h4>{objeto}</h4>
                        <h1>{qtd}</h1>
                    </div>
                    """,
                    unsafe_allow_html=True
                )

# ======================
# EVENTOS
# ======================

with col_dir:

    st.subheader("🚨 Últimos Eventos")

    ultimos = eventos[-3:]

    if not ultimos:
        st.info("Nenhum evento detectado.")

    else:

        for evento in reversed(ultimos):

            st.markdown(
                f"""
                <div style="
                    border-left:5px solid #ff4b4b;
                    padding-left:15px;
                    margin-bottom:15px;
                ">
                    <b>🕒 {evento['timestamp']}</b><br>
                    Tempo do vídeo: {evento['tempo_video']}s
                </div>
                """,
                unsafe_allow_html=True
            )

            for alt in evento["alteracoes"]:

                if alt["delta"] > 0:
                    st.success(
                        f"Adicionado: {alt['objeto']}"
                    )
                else:
                    st.error(
                        f"Removido: {alt['objeto']}"
                    )

            st.divider()

# ======================
# PESQUISA
# ======================

st.subheader("🔎 Auditoria de Objetos")

objeto_pesquisa = st.text_input(
    "Pesquisar objeto",
    placeholder="Ex: cup, keyboard, person..."
)

if objeto_pesquisa:

    resultado = []

    for evento in eventos:

        for alt in evento["alteracoes"]:

            if (
                alt["objeto"].lower()
                == objeto_pesquisa.lower()
            ):

                resultado.append({
                    "Data/Hora": evento["timestamp"],
                    "Tempo Vídeo (s)": evento["tempo_video"],
                    "Objeto": alt["objeto"],
                    "Evento": (
                        "Adicionado"
                        if alt["delta"] > 0
                        else "Removido"
                    )
                })

    st.markdown(
        f"### Resultado da pesquisa: {objeto_pesquisa}"
    )

    if resultado:

        df_resultado = pd.DataFrame(resultado)

        st.dataframe(
            df_resultado,
            use_container_width=True,
            hide_index=True
        )

    else:

        st.warning(
            "Nenhum evento encontrado para este objeto."
        )
<template>
  <div id="reservations">
    <h1>Romreservasjoner</h1>
    <div id="reservation-filter-search">
      <div id="filter">
        <button>Filtrer</button>
      </div>
      <div id="search">
        <input
          type="text"
          id="search"
          class="lower-header-item"
          placeholder="Søk"
        />
      </div>
    </div>
    <div id="reservation-container">
      <div id="container-top">
        <h4>Student</h4>
        <h4>Rom</h4>
        <h4>Dato</h4>
        <h4>Klokkeslett</h4>
        <h4>Fjern</h4>
      </div>
      <div id="list-item">
        <p>Dumb Hoe Hoesen</p>
        <p>Rom 343</p>
        <p>21.09.21</p>
        <p>12.00-17.00</p>
        <p>x</p>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
#reservations {
  width: 100%;
  padding: 40px !important;
}

h1 {
  text-align: left;
  font-size: 23px;
  margin: 40px;
}

#reservation-filter-search {
  display: grid;
  grid-template-columns: 1fr 1fr;
  margin-top: 20px;
  margin-bottom: 20px;
}

#search {
  text-align: right;
}

input {
  border: 1px solid #eee;
  border-radius: 8px;
  width: 50%;
  padding: 7px 13px 7px 48px;
  box-sizing: border-box;
  position: relative;
  font-size: 16px;
  line-height: 1.5;
  flex: 1;
  background-color: #fcfdfd;
  background-image: url("data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgZmlsbD0ibm9uZSIgc3Ryb2tlPSIjNjY2IiBzdHJva2Utd2lkdGg9IjIiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCI+PGNpcmNsZSBjeD0iMTEiIGN5PSIxMSIgcj0iOCIvPjxwYXRoIGQ9Ik0yMSAyMWwtNC00Ii8+PC9zdmc+");
  background-repeat: no-repeat;
  background-position: 7px;
}

input:focus {
  border-color: rgba(0, 0, 0, 0.12);
  background-color: #fff;
  outline: none;
  box-shadow: 0 2px 2px rgba(0, 0, 0, 0.16);
}

button {
  border-radius: 4px;
  border: 1px solid #868acc;
  color: #868acc;
  text-decoration: none;
  display: block;
  cursor: pointer;
  padding: 10px;
  font-size: 1rem;
  width: 120px;
}

#container-top {
  display: grid;
  grid-template-columns: 3fr 2fr 2fr 2fr 1fr;
  border-bottom: 2px solid #8894aa;
  padding: 10px;
}

#list-item {
  display: grid;
  grid-template-columns: 3fr 2fr 2fr 2fr 1fr;
  padding: 10px;
}

[data-position="below"] .autocomplete-input[aria-expanded="true"] {
  border-bottom-color: transparent;
  border-radius: 8px 8px 0 0;
}
[data-position="above"] .autocomplete-input[aria-expanded="true"] {
  border-top-color: transparent;
  border-radius: 0 0 8px 8px;
  z-index: 2;
}
.autocomplete[data-loading="true"]:after {
  content: "";
  border: 3px solid rgba(0, 0, 0, 0.12);
  border-right-color: rgba(0, 0, 0, 0.48);
  border-radius: 100%;
  width: 20px;
  height: 20px;
  position: absolute;
  right: 12px;
  top: 50%;
  transform: translateY(-50%);
  animation: rotate 1s linear infinite;
}
.autocomplete-result-list {
  margin: 0;
  border: 1px solid rgba(0, 0, 0, 0.12);
  padding: 0;
  box-sizing: border-box;
  max-height: 296px;
  overflow-y: auto;
  background: #fff;
  list-style: none;
  box-shadow: 0 2px 2px rgba(0, 0, 0, 0.16);
}
[data-position="below"] .autocomplete-result-list {
  margin-top: -1px;
  border-top-color: transparent;
  border-radius: 0 0 8px 8px;
  padding-bottom: 8px;
}
[data-position="above"] .autocomplete-result-list {
  margin-bottom: -1px;
  border-bottom-color: transparent;
  border-radius: 8px 8px 0 0;
  padding-top: 8px;
}
.autocomplete-result {
  cursor: default;
  padding: 12px 12px 12px 48px;
  background-image: url("data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyNCIgaGVpZ2h0PSIyNCIgZmlsbD0ibm9uZSIgc3Ryb2tlPSIjY2NjIiBzdHJva2Utd2lkdGg9IjIiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCI+PGNpcmNsZSBjeD0iMTEiIGN5PSIxMSIgcj0iOCIvPjxwYXRoIGQ9Ik0yMSAyMWwtNC00Ii8+PC9zdmc+");
  background-repeat: no-repeat;
  background-position: 12px;
}
.autocomplete-result:hover,
.autocomplete-result[aria-selected="true"] {
  background-color: rgba(0, 0, 0, 0.06);
}
@keyframes rotate {
  0% {
    transform: translateY(-50%) rotate(0deg);
  }
  to {
    transform: translateY(-50%) rotate(359deg);
  }
}
</style>

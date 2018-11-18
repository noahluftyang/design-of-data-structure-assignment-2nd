import React, { PureComponent } from 'react';

import { Map, Search } from '../components';

class Home extends PureComponent {
  state = {
    way: 'elevator',
  };

  onSelectWay = e => {
    const value = e.currentTarget.value;
    this.setState(state => ({
      ...state,
      way: value,
    }));
  };

  render() {
    return (
      <>
        <Search onClick={this.onSelectWay} way={this.state.way} />
        <Map />
      </>
    );
  }
}

export default Home;

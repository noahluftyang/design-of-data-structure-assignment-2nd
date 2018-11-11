import React, { PureComponent } from 'react';

import { Search } from './components/Search';

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
        <p>310 map will be placed</p>
      </>
    );
  }
}

export default Home;
